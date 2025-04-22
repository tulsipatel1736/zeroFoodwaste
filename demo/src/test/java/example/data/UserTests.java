package example.data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


// this test class test the User class
// testing the constructor and methods in the user class work

public class UserTests {
    // create private user so test methods can use the same user
    private User user;

    // this setup runs before running tests
    // adds user to private user field above so don't have to have redundant code throughout the whole class
    @BeforeEach
    void setUp() throws InvalidUserException {
        user = new User("name", "Pass0","email@example.com", "");
    }

    @Test
    void testUserConstructor(){
        assertEquals("name", user.getName());
        assertEquals("Pass0", user.getPassword());
        assertEquals("email@example.com", user.getEmail());
    }

    @Test
    void testUserSetName(){
        user.setName("newName");
        assertEquals("newName", user.getName());

    }

    @Test
    void testUserSetPassword() throws InvalidUserException {
        user.setPassword("NewPass0");
        assertEquals("NewPass0", user.getPassword());
    }


    @Test
    void testUserSetEmail() throws InvalidUserException {
        user.setEmail("newEmail@example.com");
        assertEquals("newEmail@example.com", user.getEmail());
    }

    @Test
    void testGetUserTypeTeacher() throws InvalidUserException {
        user.setUserType("Teacher");
        assertEquals("Teacher", user.getUserType());
    }

    @Test
    void testGetUserTypeStudent() throws InvalidUserException {
        user.setUserType("Student");
        assertEquals("Student", user.getUserType());
    }


    @Test
    void testGetUserByEmail() throws InvalidUserException {
        // Create some example users
        User user1 = new User("Alice", "alice@example.com", "Teacher");
        User user2 = new User("Bob", "bob@example.com", "Teacher");
        User user3 = new User("Charlie", "charlie@example.com", "Student");

        // Create a list of the users
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        // Test getting an existing user by email
        User result1 = ChallengeDAO.getUserByEmail(userList, "bob@example.com");
        assertEquals(user2, result1);

        // Test getting a non-existing user by email
        User result2 = ChallengeDAO.getUserByEmail(userList, "dave@example.com");
        assertNull(result2);
    }

//
//    @Test
//    void testInvalidEmailMissingAtSymbol() {
//        assertThrows(InvalidUserException.class, () -> user.setEmail("example.com"));
//    }
//
//    @Test
//    void testInvalidEmailTooManyAtSymbols(){
//        assertThrows(InvalidUserException.class, () -> user.setEmail("email@example@com"));
//    }
//
//    @Test
//    void testInvalidPasswordTooShort(){
//        assertThrows(InvalidUserException.class, () -> user.setPassword("Ps0"));
//    }
//
//    @Test
//    void testInvalidPasswordNoNumber(){
//        assertThrows(InvalidUserException.class, () -> user.setPassword("Password"));
//    }
//
//    @Test
//    void testInvalidPasswordNoUppercase(){
//        assertThrows(InvalidUserException.class, () -> user.setPassword("pass0"));
//    }
//
//    @Test
//    void testInvalidPasswordNoLowercase(){
//        assertThrows(InvalidUserException.class, () -> user.setPassword("PASS0"));
//    }
//
//    @Test
//    void testInvalidUserConstructor(){
//        // Invalid email
//        assertThrows(InvalidUserException.class, () -> new User("name", "Pass0", "example.com", ""));
//        // Invalid password
//        assertThrows(InvalidUserException.class, () -> new User("name", "Ps0", "email@example.com", ""));
//    }
//
//    @Test
//    void testInvalidUserConstructorInvalidPassword(){
//        assertThrows(InvalidUserException.class, () -> new User("name", "Ps0", "email@example.com", ""));
//    }

}
