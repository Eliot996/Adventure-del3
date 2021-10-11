import java.util.ArrayList;

public class Enemy {

    // TODO: 08/10/2021 add functionality to Class, like dies, drop items, attact,
    //  wrether or not the enemy will attact on sight, should the enemy move around?
    //  deathrattle?

    private String name;
    private int health;
    private Item weapon;
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();

    public Enemy(String name, int health, Item weapon, Room currentRoom) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.currentRoom = currentRoom;
    }

    public void takeDamage(int damage){
        health -= damage;
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
