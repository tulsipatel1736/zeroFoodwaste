package example.data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengeTests {

    // create private user so test methods can use the same user
    private Challenge challenge;

    // this setup runs before running tests
    // adds user to private user field above so don't have to have redundant code throughout the whole class
    @BeforeEach
    void setUp() throws InvalidUserException {
        challenge = new Challenge( "Banora Point", "Pizza Party Day", 30);
    }

    @Test
    void testUserConstructor(){
        assertEquals("Banora Point", challenge.getSchoolName());
        assertEquals("Pizza Party Day", challenge.getPrize());
        assertEquals(30, challenge.getChallengeDuration());
    }

    @Test
    void testSetSchoolName() throws InvalidUserException {
        challenge.setSchoolName("Banora Point");
        assertEquals("Banora Point", challenge.getSchoolName());
    }

    @Test
    void testSetPrize() throws InvalidUserException {
        challenge.setPrize("Pizza Party Day");
        assertEquals("Pizza Party Day", challenge.getPrize());
    }

    @Test
    void testSetChallengeDuration() throws InvalidUserException {
        challenge.setChallengeDuration(30);
        assertEquals(30, challenge.getChallengeDuration());
    }

    @Test
    void testGetParticipants() throws InvalidUserException {
        User user1 = new User("Alice", "password123", "alice@example.com", "Teacher");
        User user2 = new User("Bob", "password123", "bob@example.com", "Teacher");
        User user3 = new User("Charlie", "password123", "charlie@example.com", "Teacher");

        challenge.addParticipant(user1);
        challenge.addParticipant(user2);
        challenge.addParticipant(user3);

        List<User> participants = challenge.getParticipants();

        assertEquals(3, participants.size());
        assertTrue(participants.contains(user1));
        assertTrue(participants.contains(user2));
        assertTrue(participants.contains(user3));
    }

    @Test
    void testAddParticipant() throws InvalidUserException {
        User user1 = new User("John Doe", "password", "john.doe@example.com", "regular");
        User user2 = new User("Jane Smith", "password", "jane.smith@example.com", "regular");
        User user3 = new User("Bob Johnson", "password", "bob.johnson@example.com", "regular");

        //Challenge challenge = new Challenge("Walk 10,000 steps a day", "Challenge yourself to walk 10,000 steps every day.", 30);

        challenge.addParticipant(user1);
        challenge.addParticipant(user2);
        challenge.addParticipant(user3);

        List<User> participants = challenge.getParticipants();

        assertTrue(participants.contains(user1));
        assertTrue(participants.contains(user2));
        assertTrue(participants.contains(user3));
    }

}
