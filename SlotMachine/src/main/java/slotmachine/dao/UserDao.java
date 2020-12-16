package slotmachine.dao;

import java.sql.SQLException;
import java.util.List;
import slotmachine.domain.User;


public interface UserDao {
    
    //Database functionalities
    
    boolean create(String username);
    User login(String usermame);
    User getUser(String username) throws SQLException, ClassNotFoundException;
    boolean updateBalance(User user);
    List<User> getAll() throws ClassNotFoundException, SQLException;
}
