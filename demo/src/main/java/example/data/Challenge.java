package example.data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Challenge {

    private String schoolName;

    private String prize;

    private int challengeDuration; // in days

    private String teacherChallengeCode;

    private List<User> participants;

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int STRING_LENGTH = 6;

    // added by kim
    private User creator;

    // modified to add creator
    public Challenge(/*User creator*/ String schoolName, String prize, int challengeDuration) {
    //throws InvalidUserException {
        //this.creator = creator;
        this.schoolName = schoolName;
        this.prize = prize;
        this.challengeDuration = challengeDuration;

        // get random unique code
        this.teacherChallengeCode = generateRandomString();

        // create new list of participants
        participants = new ArrayList<>();
    }

    private static String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            sb.append(ALLOWED_CHARACTERS.charAt(index));
        }
        return sb.toString();
    }


    public String getSchoolName() {
        return this.schoolName;
    }

    public String getPrize() {
        return this.prize;
    }

    public int getChallengeDuration() {
        return this.challengeDuration;
    }

    public int getCreatorId() {
        return creator.getUserId(); // Assuming that the User class has a getId() method
    }

    public String getUniqueChallengeCode() {
        return this.teacherChallengeCode;
    }

    public List<User> getChallengeParticipants() {
        return participants;
    }

    public void addChallengeParticipant(User user) {
        participants.add(user);
        user.joinChallenge(this);
    }

    public void setSchoolName(String schoolName) {

        this.schoolName = schoolName;
    }
    public void setPrize(String prize) {

        this.prize = prize;
    }

    public void setChallengeDuration(int challengeDuration) {
        this.challengeDuration = challengeDuration;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void addParticipant(User user) {
        participants.add(user);
        user.joinChallenge(this);
    }

    public void removeParticipants(User user) {
        participants.remove(user);
        user.joinChallenge(this);
    }


}
