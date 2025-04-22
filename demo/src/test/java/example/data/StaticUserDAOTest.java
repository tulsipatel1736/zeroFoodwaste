package example.data;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

// Class to test static user data access object class
public class StaticUserDAOTest {

    // the user tests need to make user of the data access object
    private UserDAO userDAO;

   /* @BeforeEach
    void setUp(){
        userDAO = new StaticUserDAO();
    }

    // use this to make sure the database is empty after testing each method
    // call this after every single test
    @AfterEach
    void tearDown() {
        // Remove all users from the database.
        for (User user : userDAO.listUsers()) {
            userDAO.deleteUser(user.getEmail());
        }
        userDAO.close();
    }

    // test if we can add a user
    @Test
    void testAddUser() throws InvalidUserException {
        User user =new User("name", "Pass0", "email@example.com", "");
        userDAO.addUser(user);
        // check the user is added to the database
        // checking if the size of the DAO is 1 if the user is added
        assertEquals(1, userDAO.listUsers().size());
    }


    // test if we can get a user
    @Test
    void testGetUser() throws InvalidUserException {
        addTwoUsers();
        // Check that the user is found.
        assertEquals("name", userDAO.getUser("email@example.com").getName());
    }

    // test if user not found
    @Test
    void testGetUserNotFound() throws InvalidUserException {
        addTwoUsers();
        // Check that the email3 is not found.
        assertNull(userDAO.getUser("email3@example.com"));
    }


    // test if we can delete a user
    @Test
    void testDeleteUser() throws InvalidUserException {
        addTwoUsers();
        userDAO.deleteUser("email@example.com");
        // Check that the user was deleted, and that there is only one user left.
        assertNull(userDAO.getUser("email@example.com"));
        assertEquals(1, userDAO.listUsers().size());
    }

    // create two users, update the user emails and change their name and password
    @Test
    void testUpdateUser() throws InvalidUserException {
        addTwoUsers();
        User updatedUser = new User("name3", "Pass03", "email@example.com", "");
        userDAO.updateUser(updatedUser);
        // Check that the user was updated.
        assertEquals(updatedUser, userDAO.getUser("email@example.com"));
    }
    // private method to setup a user
    private void addTwoUsers() throws InvalidUserException {
        User user = new User("name", "Pass0", "email@example.com", "");
        User user2 = new User("name2", "Pass02", "email2@example.com", "");
        userDAO.addUser(user);
        userDAO.addUser(user2);
    }*/

}
