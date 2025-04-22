package example.data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Provides basic CRUD functionality needed by a data access object to store and retrieve
 * information about a User. This implementation uses a static database connection.
 */
public class StaticUserDAO implements UserDAO {
    /**
     * The singleton instance of the database connection.
     */
    private final JBDConnection connection;

    /**
     * Constructor intializes the connection.
     */

    public StaticUserDAO(String url, String username, String password) {
        //String url = "jdbc:mysql://foodwastequt.com.au:3306/foodwastequtcom_foodwaste?autoReconnect=true&useSSL=false";
        //String username = "foodwastequtcom_jadezia";
        //String password = "rY[]XPbS.B_M";

        //intialises connection field with database connection
        this.connection = DBConnection.getInstance(url, username, password);
        JBDConnection jbd; // initialize this in the constructor

        //JBDConnection connection = DBConnection.getInstance(url, username, password);


    //connection = DBConnection.getInstance();
    }
    //@Override
    public void addRememberMeToken(User user, String token) {
        // Find the user in the database.
        User existingUser = getUser(user.getEmail());

        // If the user exists, add the token to their list of remember-me tokens.
        if (existingUser != null) {

            // Get the user's current list of remember-me tokens.
            ArrayList<String> rememberMeTokens = existingUser.getRememberMeTokens();
            if (rememberMeTokens == null) {
                rememberMeTokens = new ArrayList<>();
            }

            // Add the new token to the list.
            rememberMeTokens.add(token);

            // Update the user's list of remember-me tokens in the database.
            existingUser.setRememberMeTokens(rememberMeTokens);
            updateUser(existingUser);
        }
    }
    

    @Override
    public void addUser(User user) {
        connection.getUsers().add(user);


    }

    @Override
    public User getUser(String email) {

        return connection.getUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateUser(User user) {
        deleteUser(user.getEmail());
        addUser(user);
    }

    @Override
    public void deleteUser(String email) {
        connection.getUsers().removeIf(user -> user.getEmail().equals(email));
    }

    @Override
    public Set<User> listUsers() {
        return new HashSet<>(connection.getUsers());
    }

    @Override
    public void close() {
        // No resources to close, nothing to implement.
    }
}