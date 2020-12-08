
import static org.junit.Assert.*;
import org.junit.Test;
import slotmachine.domain.User;

public class PlayerTest {
    
    User player = new User("");
    
    @Test
    public void getMoney(){
        assertEquals(20, player.getMoney());
    }
    
    @Test
    public void payUp(){
        player.payUp();
        assertEquals(25, player.getMoney());
    }
    
    @Test
    public void lose(){
        player.lose();
        assertEquals(19, player.getMoney()); 
    }
    
}
