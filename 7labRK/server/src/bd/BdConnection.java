package bd;

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class BdConnection {
    // Method to establish a database connection
    public Connection getConnection() {
        Connection connection = null;

        if (connection == null) {
            try {
                // Load database connection properties from a configuration file
                Properties props = new Properties();
                FileInputStream in = new FileInputStream("Datas/bd.properties");
                props.load(in);
                in.close();

                // Retrieve connection parameters from the properties file
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String password = props.getProperty("db.password");

                // Create a database connection using DriverManager
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException | IOException ex) {
                // Handle any exceptions that occur during the connection process
                System.out.println(ex.getMessage());
                System.out.println("BD error!");
            }
        }
        return connection;
    }

    // Method to execute a SQL query and return a ResultSet
    public ResultSet executeQuery(Connection conn, String query) {
        try {
            // Create a Statement and execute the provided SQL query
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            // Handle any exceptions that occur during query execution
            ex.printStackTrace();
            return null;
        }
    }
}
