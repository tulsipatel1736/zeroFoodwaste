package example.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentUserTests {

    @Test
    public void testConstructor() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");        Assertions.assertEquals("Test School", user.getSchool());
        Assertions.assertEquals("Test Class", user.getClassName());
        Assertions.assertEquals(3, user.getGrade());
        Assertions.assertEquals(12345, user.getStudentId());
        Assertions.assertEquals("student", user.getUserType());
        //Assertions.assertEquals("user", user.getRole());
    }

    @Test
    public void testSetSchool() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        user.setSchool("New School");
        Assertions.assertEquals("New School", user.getSchool());
        Assertions.assertThrows(InvalidUserException.class, () -> user.setSchool("123 School"));
    }

    @Test
    public void testSetClassName() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        user.setClassName("New Class");
        Assertions.assertEquals("New Class", user.getClassName());
        Assertions.assertThrows(InvalidUserException.class, () -> user.setClassName("Class!"));
    }

    @Test
    public void testSetGrade() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        user.setGrade(4);
        Assertions.assertEquals(4, user.getGrade());
        Assertions.assertThrows(InvalidUserException.class, () -> user.setGrade(7));
    }

    @Test
    public void testSetStudentId() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        user.setStudentId(54321);
        Assertions.assertEquals(54321, user.getStudentId());
    }

    // Test cases 5 Methods of Kim
    @Test
    public void testToString() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        Assertions.assertEquals("[Test School : Test Class : 3 : 12345]", user.toString());
    }

    @Test
    public void testOutOfBoundsLowerStudentPoints() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        Assertions.assertThrows(InvalidUserException.class, () -> user.setStudentPoints(0));
    }

    @Test
    public void testInBoundsStudentPoints() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        user.setStudentPoints(10);
        int studentPoints = user.getStudentPoints();
        Assertions.assertEquals(10, studentPoints);
    }

    @Test
    public void testRemoveStudentPoints() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        user.setStudentPoints(10);
        user.removeStudentPoints(4);
        int studentPoints = user.getStudentPoints();
        Assertions.assertEquals(6, studentPoints);
    }

    @Test
    public void testRemoveStudentPointsWhenNotEnoughPoints() throws InvalidUserException {
        User_Student user = new User_Student("test@example.com", "Test User", "student", "Test School", "Test Class", 3, 12345, "user");
        user.setStudentPoints(1);
        Assertions.assertThrows(InvalidUserException.class, () -> user.removeStudentPoints(2));
    }


}
