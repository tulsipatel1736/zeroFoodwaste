package example.data;
import org.testng.annotations.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class UserTest {
    private User user;
    private List<Challenge> challenges;

    @BeforeEach
    public void setUp() throws InvalidUserException {
        user = new User("John Doe", "password123", "johndoe@example.com", "user");
        challenges = new ArrayList<>();
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("John Doe", user.getName());
    }

    @Test
    public void testGetPassword() {
        Assertions.assertEquals("password123", user.getPassword());
    }

    @Test
    public void testGetEmail() {
        Assertions.assertEquals("johndoe@example.com", user.getEmail());
    }

    @Test
    public void testGetUserType() {
        Assertions.assertEquals("user", user.getUserType());
    }

    @Test
    public void testSetName() {
        user.setName("Jane Smith");
        Assertions.assertEquals("Jane Smith", user.getName());
    }

    @Test
    public void testSetEmail() throws InvalidUserException {
        user.setEmail("janesmith@example.com");
        Assertions.assertEquals("janesmith@example.com", user.getEmail());
    }

    @Test
    public void testSetPassword() throws InvalidUserException {
        user.setPassword("newpassword123");
        Assertions.assertEquals("newpassword123", user.getPassword());
    }

    @Test
    public void testJoinChallenge() throws InvalidUserException {
        //Challenge challenge = new Challenge("challenge1", "description", 10);
        //user.joinChallenge(challenge);
        //Assertions.assertTrue(user.getChallenges().contains(challenge));
    }

    @Test
    public void testGetUserByEmail() {
        List<User> users = new ArrayList<>();
        users.add(user);

        User foundUser = User.getUserByEmail(users, "johndoe@example.com");
        Assertions.assertEquals(user, foundUser);
    }
}
