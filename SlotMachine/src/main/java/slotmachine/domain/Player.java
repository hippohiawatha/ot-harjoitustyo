package slotmachine.domain;

public class Player {
    
    private int money;
    
    public Player(){
        money = 20;
    }
    
    public int getMoney(){
        return money;
    }
    
    public void payUp(){
        money += 5;
    }
    
    public void lose() {
        money -= 1;
    }
}