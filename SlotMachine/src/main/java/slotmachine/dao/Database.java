package slotmachine.dao;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

/**
 * 
 * handles connection to database
 */
public class Database {
    
    //Opens database connection and creates new db and/or table if needed
    
    private String dbUrl;
    
    /**
     * sets database's url
     * @param test boolean value of wether program is ran or tested
     */
    public Database(boolean test) {
        if (test) {
            dbUrl = "jdbc:sqlite:test.db";
        } else {
            dbUrl = "jdbc:sqlite:main.db";
        }
    }
    
    /**
     * opens connetion to database
     * @return Connection
     */
    public Connection connection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        return c;
    }
    
    /**
     * creates new table of Users if it doesn't exsist
     */
    public void initializeDb() {
        try {
            Connection conn = connection();
            Statement s = conn.createStatement();
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, username TEXT NOT NULL UNIQUE, balance INTEGER)");
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * deletes test database
     */
    public void deleteTestDB() {
        try {
            Path p = FileSystems.getDefault().getPath("test.db");
            Files.delete(p);
            System.out.println("Test database removed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Test db deletion failed");
        }
    }
}
