
import static org.junit.Assert.*;
import org.junit.Test;
import slotmachine.domain.Player;

public class PlayerTest {
    
    Player player = new Player();
    
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
