package slotmachine.domain;

import java.sql.*;

public class Database {
    
    public Connection connection() {
        Connection db = null;
        try {
            db = DriverManager.getConnection("jdbc:sqlite:testi.db");
            return db;
        } catch (SQLException e) {
            return db;
        }
    }
    
    public boolean initializeDb() {
        try {
            Statement s = connection().createStatement();
            s.execute("CREATE TABLE Users (id INTEGER PRIMARY KEY, username TEXT NOT NULL UNIQUE, balance INTEGER)");
           
            return true;
        } catch (SQLException e) {
            System.out.println("init bd failed");
            return false;
        }
    }
}
