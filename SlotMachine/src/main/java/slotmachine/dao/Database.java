package slotmachine.dao;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class Database {
    
    //Opens database connection and creates new db and/or table if needed
    
    private String dbUrl;
    
    public Database(boolean test) {
        if (test) {
            dbUrl = "jdbc:sqlite:test.db";
        } else {
            dbUrl = "jdbc:sqlite:main.db";
        }
    }
    
    public Connection connection() {
        Connection c = null;
        try {
            c = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            System.out.println("Connection failed");
        }
        return c;
    }
    
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
