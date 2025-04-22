package example.data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class StaticUserDAOTests {

    private UserDAO userDAO;

    /*@BeforeEach
    void setUp(){
        userDAO = new StaticUserDAO();

    }*/

    @Test
    void testAddUser() throws InvalidUserException {
        User user = new User("John Doe", "password", "john.doe@example.com", "regular");
    }

}
