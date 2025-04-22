package example.data;

/**
 * Provides a singleton instance of the database connection.
 */
public class DBConnection {
    /**
     * The singleton instance of the database connection.
     */
    private static JBDConnection instance = null;

    /**
     * Constructor initializes the connection.
     *
     * @param url      the URL of the database
     * @param username the username for the database
     * @param password the password for the database
     */
    private DBConnection(String url, String username, String password) {
        instance = new JBDConnection(url, username, password);
    }

    /**
     * Provides global access to the singleton instance of the JBDConnection.
     *
     * @param url      the URL of the database
     * @param username the username for the database
     * @param password the password for the database
     * @return a handle to the singleton instance of the JBDConnection.
     */
    public static JBDConnection getInstance(String url, String username, String password) {
        if (instance == null) {
            instance = new DBConnection(url, username, password).instance;
        }
        return instance;
    }
}
