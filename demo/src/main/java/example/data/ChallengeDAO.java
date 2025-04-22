package example.data;

import java.util.List;
import java.util.Set;

/**
 * Provides basic CRUD functionality needed by a data access object to store and retrieve
 * information about a Challenge.
 */
public interface ChallengeDAO {

    public static User getUserByEmail(List<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Adds a Challenge to the challenge list, if they are not already in the list
     * @param challenge User to add
     */
    void addChallenge(Challenge challenge);

    /**
     * Extracts all the details of a challenge based on unique code
     * @param teacherChallengeCode The unique code to search for.
     * @return all details in a challenge object for the unique code
     */
    Challenge getChallengeByTeacherCode(String teacherChallengeCode);

    /**
     * Updates a challenge in the user list.
     * @param challenge to update
     */
    void updateChallenge(Challenge challenge);

    /**
     * Deletes a Challenge
     * @param teacherChallengeCode Use unique code to delete challenge
     */
    void deleteChallenge(String teacherChallengeCode);

    /**
     * Gets a list of all the challenges
     * @return A Set of all the challenges
     */
    Set<Challenge> listChallenges();

    /**
     * Finalizes any resources used by the data source and ensures data is
     * persisited.
     */
    void close();

}
