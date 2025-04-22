package example.data;

import java.util.ArrayList;
import java.util.Collection;

public interface UserChallengeDAO {

    /**
     * Adds a challenge for a given user.
     * @param user the user to associate the challenge with.
     * @param challenge the challenge to add.
     */
    void addChallenge(User user, Challenge challenge);

    /**
     * Updates a challenge for a given user.
     * @param user the user associated with the challenge.
     * @param challenge the challenge to update.
     */
    void updateChallenge(User user, Challenge challenge);

    /**
     * Retrieves all challenges associated with a given user.
     * @param user the user to retrieve the challenges for.
     * @return a collection of challenges associated with the given user.
     */
    Collection<Challenge> getChallengesForUser(User user);

    ArrayList<Challenge> getChallenges(String uniqueCode);
}
