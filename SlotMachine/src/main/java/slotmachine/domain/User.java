package slotmachine.domain;

public class User {
    
    private int money;
    private final String name;
    
    public User(String name) {
        this.name = name;
        this.money = 20;
    }
    
    public int getMoney() {
        return money;
    }
    
    public void setMoney(int amount) {
        money = amount;
    }
    
    public void lose() {
        money -= 1;
    }
    
    public void payUp() {
        money += 5;
    }
    
    public String getName() {
        return name;
    }
}