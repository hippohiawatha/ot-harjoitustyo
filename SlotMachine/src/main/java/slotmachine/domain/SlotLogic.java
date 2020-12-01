package slotmachine.domain;

import java.util.Random;

public class SlotLogic {
    
    Random random = new Random();
    
    private int[][] slots;
    private boolean won;
    
    public SlotLogic() {
        slots = new int[3][3];
        setValueSlots();
    }
    
    public int[][] getSlots() {
        return slots;
    }
    
    public int getValueSlot(int x, int y) {
        return slots[x][y];
    }
    
    public void setValueSlots() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                slots[i][j] = random.nextInt(7) + 1;
            }
        }
    }
    
    public boolean getWin() {
        checkWin();
        return won;
    }
    
    public void checkWin() {
        if (slots[0][0] == slots[0][1] && slots[0][1] == slots[0][2]) {
            won = true;
        } else if (slots[1][0] == slots[1][1] && slots[1][1] == slots[1][2]) {
            won = true;
        } else if (slots[2][0] == slots[2][1] && slots[2][1] == slots[2][2]) {
            won = true;
        } else {
            won = false;
        }
    }
    
}