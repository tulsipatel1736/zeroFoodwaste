package example.data;

public class User_Student extends User {

    private String school;
    private String className;
    private int grade;
    private int studentId;
    private int studentPoints;

    public User_Student(String email, String name, String user_type, String school, String className, int grade, int studentId, String role) throws InvalidUserException {
        super(name, null, email, user_type);
        this.school = school;
        this.className = className;
        this.grade = grade;
        this.studentId = studentId;
    }


    public String getSchool() {
        return school;
    }

    public String getClassName() {
        return className;
    }

    public int getGrade() {
        return grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getStudentPoints() {
        return studentPoints;
    }

    public void setSchool(String school) throws InvalidUserException {
        if (school.matches(".*[\\d\\p{Punct}].*")) {
            throw new InvalidUserException("School name cannot contain numbers or punctuation marks");
        }
        this.school = school;
    }

    public void setClassName(String className) throws InvalidUserException {
        if (className.matches(".*[^\\w\\d\\s-].*")) {
            throw new InvalidUserException("Class name cannot contain punctuation marks other than space and dash");
        }
        this.className = className;
    }

    public void setGrade(int grade) throws InvalidUserException {
        if (grade < 1 || grade > 6) {
            throw new InvalidUserException("Grade can only be between 1 - 6");
        }
        this.grade = grade;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    // 5 Methods by Kim:
    public String toString() {

        StringBuilder sbStudent = new StringBuilder();

        sbStudent.append("[");

        sbStudent.append(getSchool());
        sbStudent.append(" : ");
        sbStudent.append(getClassName());
        sbStudent.append(" : ");
        sbStudent.append(getGrade());
        sbStudent.append(" : ");
        sbStudent.append(getStudentId());

        sbStudent.append("]");

        return sbStudent.toString();
    }

    public void setStudentPoints(int studentPoints) throws InvalidUserException {

        if (studentPoints < 1) {
            throw new InvalidUserException("Student Points must be bigger than 0");
        }

        this.studentPoints = this.studentPoints + studentPoints;
    }

    public void removeStudentPoints(int studentPointsToRemove) throws InvalidUserException {

        if (studentPointsToRemove <= this.studentPoints) {
            this.studentPoints = this.studentPoints - studentPointsToRemove;
        }
        else
        {
            throw new InvalidUserException("Student must have more than 0 points.");
        }
    }


    }


