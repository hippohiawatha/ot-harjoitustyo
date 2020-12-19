package slotmachine.domain;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * handles logic behind slots and what is seen by user
 */
public class SlotLogic {
    
    //Initializes and takes care of the slotmachine's functionalities
    
    Random random = new Random();
    
    private int[][] slots;
    private boolean won;
    
    /**
     * initialized SlotLogic
     */
    public SlotLogic() {
        slots = new int[3][3];
        setValueSlots();
    }
    
    /**
     * 
     * @return matrix of type int 
     */
    public int[][] getSlots() {
        return slots;
    }
    
    /**
     * 
     * @param x x coordinate for slot
     * @param y y coordinate for slot
     * @return slot in position (x,y)
     */
    public int getValueSlot(int x, int y) {
        return slots[x][y];
    }
    
    /**
     * sets values for all slots
     */
    public void setValueSlots() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                slots[i][j] = random.nextInt(7) + 1;
            }
        }
    }
    
    /**
     * 
     * @return boolean of wether game is won or not
     */
    public boolean getWin() {
        checkWin();
        return won;
    }
    
    /**
     * check if any of the rows have won
     */
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
    
    /**
     * 
     * @param fruit string value of the wanted fruit
     * @return Image of given fruit
     */
    public Image getImage(String fruit) {
        return new Image("images/fruits/" + fruit + ".png", 100, 100, false, false);
    }
    
    /**
     * 
     * @param x int of slots value
     * @return ImageView of given int
     */
    public ImageView imageHandler(int x) {
        switch (x) {
            case 1: 
                return new ImageView(getImage("pineapple"));
            case 2: 
                return new ImageView(getImage("pear"));
            case 3: 
                return new ImageView(getImage("apple"));
            case 4: 
                return new ImageView(getImage("orange"));
            case 5: 
                return new ImageView(getImage("cherry"));
            case 6: 
                return new ImageView(getImage("strawberry"));
            case 7: 
                return new ImageView(getImage("watermelon"));
        }
        return null;
    }
    
    /**
     * 
     * @return boolean of win
     */
    public boolean won() {
        checkWin();
        return won;  
    }
    
}