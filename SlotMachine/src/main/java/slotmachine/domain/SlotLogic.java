package slotmachine.domain;

import java.net.URL;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SlotLogic {
    
    //Initializes and takes care of the slotmachine's functionalities
    
    Random random = new Random();
    
    private Image image;
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
    
    //Just to make imageHandler a bit cleaner
    public Image getImage(String fruit) {
        return new Image("images/fruits/" + fruit + ".png", 100, 100, false, false);
    }
    
    //Assign images to grid values
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
    
    public boolean won() {
        checkWin();
        return won;  
    }
    
}