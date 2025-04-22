package example.data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentLeaderBoardTests {

    @Test
    public void testAddStudent() throws InvalidUserException {
        StudentLeaderBoard studentLeaderBoard = new StudentLeaderBoard();

        User_Student student1 = new User_Student("student1@gmail.com", "sarah", "student", "qut", "class1", 1, 1234, "role1");
        studentLeaderBoard.addStudent(student1);

        Assertions.assertEquals(1, studentLeaderBoard.getStudents().size());
        Assertions.assertTrue(studentLeaderBoard.getStudents().contains(student1));
    }

    @Test
    public void testRemoveStudent() throws InvalidUserException {
        StudentLeaderBoard studentLeaderBoard = new StudentLeaderBoard();

        User_Student student1 = new User_Student("student2@gmail.com", "peter", "student", "qut", "class1", 1, 1384, "role1");
        studentLeaderBoard.addStudent(student1);

        studentLeaderBoard.removeStudent(student1);

        Assertions.assertEquals(0, studentLeaderBoard.getStudents().size());
        Assertions.assertFalse(studentLeaderBoard.getStudents().contains(student1));
    }

}
