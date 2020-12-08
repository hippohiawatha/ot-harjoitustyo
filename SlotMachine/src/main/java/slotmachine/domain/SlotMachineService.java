package slotmachine.domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import slotmachine.dao.UserDao;


public class SlotMachineService implements UserDao {
    
    private final Database db  = new Database();
    private Statement s;
    
    
    @Override
    public boolean create(String username) {
        db.initializeDb();
        try {
            PreparedStatement p = db.connection().
                    prepareStatement("INSERT INTO Users (username, balance) VALUES (?, ?)");
            p.setString(1, username);
            p.setInt(2, 20);
            p.execute();
            System.out.println("User added to database!");
            return true;
        } catch (SQLException e) {
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
        for(User u : getAll()) {
            if(u.getName().equals(username)) {
                try {
                    PreparedStatement p = db.connection().
                            prepareStatement("SELECT balance FROM Users WHERE username = ?");
                    p.setString(1, username);
                    ResultSet rs = p.executeQuery();
                    u.setMoney(rs.getInt("balance"));
                    System.out.println(u.getMoney());
                    return u;
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                    return null;
                }
            }
        }
        return user;
    }
    
    //was in use sooner, we'll see if needed later
    /*
    @Override
    public User payUp(User user, int amount) {
        int balance = user.getMoney() + amount;
        try {
            PreparedStatement p = db.connection().
                    prepareStatement("UPDATE Users SET balance = ? WHERE username = ?");
            p.setInt(1, user.getMoney() + amount);
            p.setString(2, user.getName());
            p.executeUpdate();
            user.setMoney(balance);
            return user;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    Override
    public User taxLose(User user, int amount) {
        int balance = user.getMoney() - amount;
        try {
            PreparedStatement p = db.connection().
                    prepareStatement("UPDATE Users SET balance = ? WHERE username = ?");
            p.setInt(1, balance);
            p.setString(2, user.getName());
            p.executeUpdate();
            user.setMoney(balance);
            return user;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    */

    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
        s = db.connection().createStatement();
        List<User> users = new ArrayList<>();
        try {
            ResultSet result = s.executeQuery("SELECT * FROM Users");
            while (result.next()) {
                String username = result.getString("username");
                users.add(new User(username));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public boolean updateBalance(User user) {
        try {
            PreparedStatement p = db.connection().
                    prepareStatement("UPDATE Users SET balance = ? WHERE username = ?");
            p.setInt(1, user.getMoney());
            p.setString(2, user.getName());
            p.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
