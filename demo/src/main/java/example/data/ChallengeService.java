package example.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static example.data.User.getUserByEmail;

public class ChallengeService {
    private List<Challenge> challenges;
    private Map<String, User> users;

    public ChallengeService() {
        challenges = new ArrayList<>();
        users = new HashMap<>();
    }
//
//    public Challenge createChallenge(User creator) throws InvalidUserException {
//        Challenge challenge = new Challenge(creator, "", "", 20);
//        challenges.add(challenge);
//        return challenge;
//    }

    public Challenge getUniqueCode(String uniqueCode) {
        for (Challenge challenge : challenges) {
            if (challenge.getUniqueChallengeCode().equals(uniqueCode)) {
                return challenge;
            }
        }
        return null;
    }
}