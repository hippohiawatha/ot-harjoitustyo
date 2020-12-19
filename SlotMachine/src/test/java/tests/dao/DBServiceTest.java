package tests.dao;

import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import slotmachine.dao.DBService;
import slotmachine.domain.User;

public class DBServiceTest {
    
    private DBService DBService;
    
    @Before
    public void setUp() {
        DBService = new DBService(true);
        DBService.create("test");
    }
    
    @After
    public void terminate() {
        DBService.deleteTestDB();
    }
    
    @Test
    public void testCreate() throws SQLException, ClassNotFoundException {
        assertTrue(DBService.create("test1"));
    }
    
    @Test
    public void getAll() throws SQLException, ClassNotFoundException {
        List<User> users = DBService.getAll();
        assertEquals(1, users.size());
        User u = users.get(0);
        assertEquals("test", u.getName());
    }
    
    @Test
    public void testGetUser() throws SQLException, ClassNotFoundException {
        User u = DBService.getUser("test");
        assertEquals("test", u.getName());
    }
    
    @Test
    public void testFakeGetUser() throws SQLException, ClassNotFoundException {
        assertEquals(null, DBService.getUser("null"));
    }
    
    @Test
    public void testLogin() {
        User u = DBService.login("test");
        assertEquals("test", u.getName());
    }
    
    @Test
    public void testFakeLogin() {
        assertEquals(null, DBService.login("null"));
    }
    
    @Test
    public void testUpdateBalance() throws SQLException, ClassNotFoundException {
        User u = DBService.getUser("test");
        assertTrue(DBService.updateBalance(u));
    }
}
