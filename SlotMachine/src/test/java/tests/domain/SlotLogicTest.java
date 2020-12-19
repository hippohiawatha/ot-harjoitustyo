package tests.domain;


import javafx.scene.image.Image;
import static org.junit.Assert.*;
import org.junit.Test;
import slotmachine.domain.SlotLogic;


public class SlotLogicTest {
    
    SlotLogic slot = new SlotLogic();
    
    @Test
    public void slotsAreCreated() {
        assertTrue(slot.getSlots().length == 3);
    }
    
    @Test
    public void getValueSlotWorks() {
        int first = slot.getSlots()[1][1];
        int value = slot.getValueSlot(1, 1);
        assertEquals(value, first);
    }
    
    @Test
    public void testWin1(){
        for (int i = 0; i < 3; i++) {
            slot.getSlots()[0][i] = 1;
        }
        assertTrue(slot.getWin());
       
    }
    
    @Test
    public void testWin2() {
        slot.getSlots()[0][0] = 8;
        for (int i = 0; i < 3; i++) {
            slot.getSlots()[1][i] = 1;
        }
        assertTrue(slot.getWin());
       
    }
    
    @Test
    public void testWin3() {
        slot.getSlots()[0][0] = 8;
        slot.getSlots()[1][0] = 8;
        for (int i = 0; i < 3; i++) {
            slot.getSlots()[2][i] = 1;
        }
        assertTrue(slot.getWin());
       
    }
    
    @Test
    public void testLose() {
        slot.getSlots()[0][1] = 8;
        slot.getSlots()[1][1] = 8;
        slot.getSlots()[2][1] = 8;
        assertFalse(slot.getWin());
    }
    
    @Test
    public void testWon() {
        for (int i = 0; i < 3; i++) {
            slot.getSlots()[0][i] = 1;
        }
        assertTrue(slot.won());
    }
    
}
