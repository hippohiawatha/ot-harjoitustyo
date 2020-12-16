package slotmachine.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import slotmachine.domain.User;


public class DBService implements UserDao {
    
    //Implements UserDao defined functionalities
    
    private Database db;
    private Statement s;
    
    public DBService(boolean test) {
        db = new Database(test);
        db.initializeDb();
    }
    
    @Override
    public boolean create(String username) {
        try {
            Connection conn = db.connection();
            PreparedStatement p = conn.prepareStatement("INSERT INTO Users (username, balance) VALUES (?, ?)");
            p.setString(1, username);
            p.setInt(2, 20);
            p.execute();
            conn.close();
            System.out.println("User added to database!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error creating user");
            return false;
        }
    }

    @Override
    public User login(String username) {
        try {
            User user = getUser(username);
            return user;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error logging in");
            return null;
        }
    }

    @Override
    public User getUser(String username) throws SQLException, ClassNotFoundException {
        User user = null;
        for (User u : getAll()) {
            if (u.getName().equals(username)) {
                try {
                    Connection conn = db.connection();
                    PreparedStatement p = conn.prepareStatement("SELECT balance FROM Users WHERE username = ?");
                    p.setString(1, username);
                    ResultSet rs = p.executeQuery();
                    u.setMoney(rs.getInt("balance"));
                    conn.close();
                    return u;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return null;
                }
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
        Connection conn = db.connection();
        s = conn.createStatement();
        List<User> users = new ArrayList<>();
        try {
            ResultSet result = s.executeQuery("SELECT * FROM Users");
            while (result.next()) {
                String username = result.getString("username");
                users.add(new User(username));
            }
            s.close();
            conn.close();
        } catch (SQLException e) {
            s.close();
            conn.close();
            System.out.println(e.getMessage());
            System.out.println("Error in getAll()");
        }
        return users;
    }

    @Override
    public boolean updateBalance(User user) {
        try {
            Connection conn = db.connection();
            PreparedStatement p = conn.prepareStatement("UPDATE Users SET balance = ? WHERE username = ?");
            p.setInt(1, user.getMoney());
            p.setString(2, user.getName());
            p.executeUpdate();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error in updateBalance()");
            return false;
        }
    }
    
    public void deleteTestDB(){
        db.deleteTestDB();
    }
    
}
