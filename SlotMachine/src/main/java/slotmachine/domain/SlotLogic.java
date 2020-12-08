package slotmachine.domain;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

public class SlotLogic {
    
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
        Image i = new Image(getClass().getResourceAsStream("/images/fruits/" + fruit + ".png"), 100, 100, false, false);
        return i;
    }
    
    //Assign images to grid values
    public ImageView imageHandler(int x) {
        switch (x) {
            case 1: 
                image = getImage("pineapple");
                break;
            case 2: 
                image = getImage("pear");
                break;
            case 3: 
                image = getImage("apple");
                break;
            case 4: 
                image = getImage("orange");
                break;
            case 5: 
                image = getImage("cherry");
                break;
            case 6: 
                image = getImage("strawberry");
                break;
            case 7: 
                image = getImage("watermelon");
                break;
        }
        return new ImageView(image);
    }
    
    public boolean won() {
        checkWin();
        return won;  
    }
    
}