import java.util.ArrayList;

public class Enemy {

    // TODO: 08/10/2021 add functionality to Class, like dies, drop items, attact,
    //  wrether or not the enemy will attact on sight, should the enemy move around?

    private String name;
    private int health;
    private Item weapon;
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();
    private boolean isAgressive;

    public boolean isAgressive() {
        return isAgressive;
    }

    public Enemy(String name, int health, Item weapon, Room currentRoom, boolean isAgressive) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;
        this.isAgressive = isAgressive;
    }

    public void takeDamage(int damage){
        health -= damage;
        if (!isAgressive){
            isAgressive = true;
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Item getWeapon() {
        return weapon;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
