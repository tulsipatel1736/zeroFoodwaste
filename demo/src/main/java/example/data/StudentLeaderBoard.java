package example.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentLeaderBoard {
    private List<User_Student> students;

    public StudentLeaderBoard() {
        students = new ArrayList<>();
    }

    public void addStudent(User_Student student) {
        students.add(student);
    }

    public void removeStudent(User_Student student) {
        students.remove(student);
    }

    public List<User_Student> getStudents() {
        return students;
    }

    public void sortStudentsByPoints() {
        Collections.sort(students, Comparator.comparingInt(User_Student::getStudentPoints).reversed());
    }

    public void clear() {
        students.clear();
    }
}
