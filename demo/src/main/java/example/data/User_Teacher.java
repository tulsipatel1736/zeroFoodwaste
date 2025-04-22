package example.data;

public class User_Teacher extends User {

    private String school;
    private String className;
    private int grade;

    // constructor
    public User_Teacher(String email, String name, String user_type, String school, String className, int grade) throws InvalidUserException {
        super(name, email, user_type);
        this.school = school;
        this.className = className;
        this.grade = grade;
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


    public void setSchool(String school) throws InvalidUserException {
        if (school.matches(".*[\\d\\p{Punct}].*")) {
            throw new InvalidUserException("School name cannot contain numbers or punctuation marks");
        }


        this.school = school;
    }

    public void setClassName(String className) throws InvalidUserException {
        if (className.matches(".*[\\p{Punct}&&[^\\s-]].*")) {
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


}
