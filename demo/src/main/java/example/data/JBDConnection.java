package example.data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JBDConnection {
   private String url;
   private String username;
   private String password;
   private final ArrayList<User> users;
   private final ArrayList<Challenge> challenges;


   public JBDConnection(String url, String username, String password) {
      this.url = url;
      this.username = username;
      this.password = password;
      users = new ArrayList<>();
      challenges = new ArrayList<>();
   }

   public static void main(String[] args) {
      String url = "jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false";
      String username = "foodwastequtcom_jadezia";
      String password = "rY[]XPbS.B_M";

      try (Connection conn = DriverManager.getConnection(url, username, password)) {
         System.out.println("Connected to database!");
      } catch (SQLException e) {
         System.err.println("Failed to connect to database :(");
         e.printStackTrace();
      }
   }


   public void insertUser(User user) {
      String sql = "INSERT INTO Users (ClassID, Name, Email, Password, UserType, UserID) VALUES " +
              "(NULL, '" +
              user.getName() + "', '" +
              user.getEmail() + "', '" +
              user.getPassword() + "', '" +
              user.getUserType() + "', " +
              user.getUserId() + ")";
      try (Connection conn = DriverManager.getConnection(url, username, password);
           Statement stmt = conn.createStatement()) {
         int rowsAffected = stmt.executeUpdate(sql);
         if (rowsAffected == 1) {
            System.out.println("User inserted successfully!");
         } else {
            System.out.println("Error inserting user. No rows affected.");
         }
      } catch (SQLException e) {
         System.err.println("Failed to insert user: " + e.getMessage());
      }
   }

   /**
    * Gets the "tables" of users as a list.
    *
    * @return The list of users.
    */
   public ArrayList<User> getUsers() {
      return users;
   }


   /**
    * Gets user from cpanel database based on email and password
    */
   // Marnie working here - Don't chane this.
   // Login controller relies on this to login and register a year
   // Retrieves user from a database based on their email and password
   public User getUser(String email, String pw) {
      String sql = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
      System.out.println(sql);
      System.out.println(email);
      System.out.println(pw);

      try (Connection conn = DriverManager.getConnection(url, username, password);
           PreparedStatement stmt = conn.prepareStatement(sql)) {
         System.out.println("Connected to the database");
         stmt.setString(1, email);
         stmt.setString(2, pw);
         try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
               int id = rs.getInt("UserID");
               String name = rs.getString("Name");
               String userType = rs.getString("UserType");

               System.out.println("Retrieving user information");
               System.out.println("UserID " + id);
               System.out.println("Name " + name);
               System.out.println("Pw " + pw);
               System.out.println("Email " + email);
               System.out.println("UserType " + userType);
               return new User(name, pw, email, userType);
            }
         }
      } catch (SQLException e) {
         System.err.println("Failed to retrieve user: " + e.getMessage());
      } catch (InvalidUserException e) {
         throw new RuntimeException(e);
      }

      return null;
   }


   public ArrayList<Challenge> getChallenges() {
      return challenges;
   }


   // this method is called from DeleteUsercontroller to delete user
   public void deleteUser(String email, String pw) {
      System.out.println("Inside the deleteUser in JBDConnection class");

      String sql = "DELETE FROM Users WHERE Email = ? AND Password = ?";
      System.out.println(sql);
      System.out.println(email);
      System.out.println(pw);

      try (Connection conn = DriverManager.getConnection(url, username, password);
           PreparedStatement stmt = conn.prepareStatement(sql)) {
         System.out.println("Connected to the database");
         stmt.setString(1, email);
         stmt.setString(2, pw);
         stmt.executeUpdate();
         System.out.println("User deleted from database");
      } catch (SQLException e) {
         System.err.println("Failed to delete user: " + e.getMessage());
      }
   }

   public void insertChallenge(Challenge challenge) {
      String sql = "INSERT INTO Challenge (schoolName, prize, challengeDuration) VALUES (?, ?, ?)";
      try (Connection conn = DriverManager.getConnection(url, username, password);
           PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1, challenge.getSchoolName());
         stmt.setString(2, challenge.getPrize());
         stmt.setInt(3, challenge.getChallengeDuration());
         int rowsAffected = stmt.executeUpdate();
         if (rowsAffected == 1) {
            System.out.println("Challenge inserted successfully!");
         } else {
            System.out.println("Error inserting challenge. No rows affected.");
         }
      } catch (SQLException e) {
         System.err.println("Failed to insert challenge: " + e.getMessage());
      }
   }
   }