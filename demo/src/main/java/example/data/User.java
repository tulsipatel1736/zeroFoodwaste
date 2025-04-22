package example.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.stream.StreamSupport;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a user of the application.
 */
@Component
public class User {
    private String name;
    private String password;
    private String email;
    private ArrayList<String> rememberMeTokens;

    private String userType;
    private List<Challenge> challenges;
    private static int userId = -1;

    public User(String name, String password, String email, String userType) throws InvalidUserException {
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
        this.setUserType(userType);
        challenges = new ArrayList<>();
        this.rememberMeTokens = new ArrayList<>();
    }

    public User() {
    }

    @Autowired
    public User(String name, String email, String userType) throws InvalidUserException {
        this.name = name;
        this.setEmail(email);
        this.setUserType(userType);
        challenges = new ArrayList<>();
    }


    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
//        System.out.println("Returning password: " + password);
        return password;
    }

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
//        System.out.println("Returning email: " + email);
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) throws InvalidUserException {
        if (userType.matches(".*[\\d\\p{Punct}].*")) {
            throw new InvalidUserException("Role name cannot contain numbers or punctuation marks");
        }

        this.userType = userType;
    }


    /**
     * Sets the name of the user.
     *
     * @param newName The new name of the user.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Sets the password of the user.
     *

     */
    public void setEmail(String email) throws InvalidUserException {
        this.email = email;
    }

    public void setPassword(String password) throws InvalidUserException {
        this.password = password;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void joinChallenge(Challenge challenge) {
        challenges.add(challenge);
    }

    public static User getUserByEmail(List<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<String> getRememberMeTokens() {
        return rememberMeTokens;
    }

    public void setRememberMeTokens(ArrayList<String> rememberMeTokens) {
    }


    public static int getUserId() {
        if(userId == -1) {
            userId = readUserIdFromFile();
        }
        int newUserId = ++userId;
        writeUserIdToFile(newUserId);
        return newUserId;
    }

    // these three functions get user id from userId.txt file, read the item in it
    // increment it and replace it within the file
    // this is used to generate a user.ID to save in the database
    private static int readUserIdFromFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("userId.txt"));
            String line = br.readLine();
            System.out.println("User id to be updated:" + line);
            return Integer.parseInt(line);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static void writeUserIdToFile(int newUserId) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("userId.txt"));
            bw.write(String.valueOf(newUserId));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String email) throws SQLException {
        System.out.println("Inside delete user account");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false", "foodwastequtcom_jadezia", "rY[]XPbS.B_M")) {
            String sql = "DELETE FROM users WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.executeUpdate();
            }
        }
        // Redirect to "/login" page
        // Replace the following line with your own implementation of redirection
        System.out.println("User deleted. Redirecting to /login");
    }


}
