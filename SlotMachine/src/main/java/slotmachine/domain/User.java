package slotmachine.domain;

/**
 * 
 * construction of program's user
 */
public class User {
    
    private int money;
    private final String name;
    
    /**
     * creates new User
     * @param name username
     */
    public User(String name) {
        this.name = name;
        this.money = 20;
    }
    
    /**
     * 
     * @return int of users money
     */
    public int getMoney() {
        return money;
    }
    
    /**
     * 
     * @param amount int values of new balance
     */
    public void setMoney(int amount) {
        money = amount;
    }
    
    /**
     * tax user for losing
     */
    public void lose() {
        money -= 1;
    }
    
    /**
     * pay win to user
     */
    public void payUp() {
        money += 5;
    }
    
    /**
     * 
     * @return users name as string
     */
    public String getName() {
        return name;
    }
}