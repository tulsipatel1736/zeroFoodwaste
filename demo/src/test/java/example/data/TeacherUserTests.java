package example.data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// this test class test the teacher class
// testing the constructor and methods in the user class work

public class TeacherUserTests {

    // create private user so test methods can use the same user
    private User_Teacher user;

    // this setup runs before running tests
    // adds user to private user field above so don't have to have redundant code throughout the whole class
    @BeforeEach
    void setUp() throws InvalidUserException {
        user = new User_Teacher("email@example.com", "Marnie", "Teacher", "Centaur", "Class 5", 6);
    }

    // test fields
    @Test
    void testUserConstructor(){
        assertEquals("email@example.com", user.getEmail());
        assertEquals("Marnie", user.getName());
        assertEquals("Teacher", user.getUserType());
        assertEquals("Centaur", user.getSchool());
        assertEquals("Class 5", user.getClassName());
        assertEquals(6, user.getGrade());
    }

    @Test
    void testTeacherSetSchool() throws InvalidUserException {
        user.setSchool("Banora Point");
        assertEquals("Banora Point", user.getSchool());
    }

    @Test
    void testUserSetClassName() throws InvalidUserException {
        user.setClassName("Class 5");
        assertEquals("Class 5", user.getClassName());
    }


    @Test
    void testUserSetGrade() throws InvalidUserException {
        user.setGrade(5);
        assertEquals(5, user.getGrade());
    }

    @Test
    void testSchoolContainsNumbers() {
        assertThrows(InvalidUserException.class, () -> user.setSchool("Centaur 04"));
    }

    @Test
    void testSchoolContainsPunctuation() {
        assertThrows(InvalidUserException.class, () -> user.setSchool("Centaur;"));
    }

    @Test
    void testClassNameContainsPunctuation() {
        assertThrows(InvalidUserException.class, () -> user.setClassName("Centaur;"));
    }


    @Test
    void testGradeDoesNotLessThanOne() {
        assertThrows(InvalidUserException.class, () -> user.setGrade(0));
    }

    @Test
    void testGradeDoesNotGreaterThanSix() {
        assertThrows(InvalidUserException.class, () -> user.setGrade(7));
    }
    @Test
    void testTeacherSetRole() throws InvalidUserException {
        user.setUserType("Supervisor");
        assertEquals("Supervisor", user.getUserType());
    }

    @Test
    void testRoleContainsNumbers() {
        assertThrows(InvalidUserException.class, () -> user.setUserType("Teacher 01"));
    }

    @Test
    void testRoleContainsPunctuation() {
        assertThrows(InvalidUserException.class, () -> user.setUserType("Teacher!"));
    }

}
