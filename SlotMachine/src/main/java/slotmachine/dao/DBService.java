package slotmachine.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import slotmachine.domain.User;

/**
 * handles database communication
 */
public class DBService implements UserDao {
    
    //Implements UserDao defined functionalities
    
    private Database db;
    private Connection conn;
    private PreparedStatement p;
    private Statement s;
    private ResultSet rs;
    
    /**
     * initializes the DBService
     * @param test boolean value of wether program is ran or tested
     */
    public DBService(boolean test) {
        db = new Database(test);
        db.initializeDb();
    }
    
    /**
     * create new use in database
     * @param username username
     * @return true if user is created succesfully
     */
    @Override
    public boolean create(String username) {
        try {
            conn = db.connection();
            p = conn.prepareStatement("INSERT INTO Users (username, balance) VALUES (?, ?)");
            p.setString(1, username);
            p.setInt(2, 20);
            p.execute();
            System.out.println("User added to database!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                p.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * login to existing user
     * @param username username
     * @return User or null, if given username is not in database
     */
    @Override
    public User login(String username) {
        try {
            User user = getUser(username);
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * helper method for others
     * @param username username
     * @return User or null, if given username is not in database
     * @throws SQLException SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    @Override
    public User getUser(String username) throws SQLException, ClassNotFoundException {
        for (User u : getAll()) {
            if (u.getName().equals(username)) {
                try {
                    conn = db.connection();
                    p = conn.prepareStatement("SELECT balance FROM Users WHERE username = ?");
                    p.setString(1, username);
                    rs = p.executeQuery();
                    u.setMoney(rs.getInt("balance"));
                    return u;
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    return null;
                } finally {
                    p.close();
                    rs.close();
                    conn.close();
                }
            }
        }
        return null;
    }

    /**
     * helper method for others
     * @return List of all users
     * @throws ClassNotFoundException ClassNotFoundException
     * @throws SQLException SQLException
     */
    @Override
    public List<User> getAll() throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();
        try {
            conn = db.connection();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                String username = rs.getString("username");
                users.add(new User(username));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            s.close();
            rs.close();
            conn.close();
        }
        return users;
    }

    /**
     * updates given users balance
     * @param user User
     * @return true if users balance was updated successfully
     */
    @Override
    public boolean updateBalance(User user) {
        try {
            conn = db.connection();
            p = conn.prepareStatement("UPDATE Users SET balance = ? WHERE username = ?");
            p.setInt(1, user.getMoney());
            p.setString(2, user.getName());
            p.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                p.close();
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        }
    }
    
    /**
     * delete the database created for testing
     */
    public void deleteTestDB() {
        db.deleteTestDB();
    }
}
