import java.util.ArrayList;

public class Enemy extends Character{

    // TODO: 08/10/2021 add functionality to Class, like dies, drop items, should the enemy move around?

    private String name;
    private int health;
    private Room currentRoom;
    private ArrayList<Item> inventory = new ArrayList<>();
    private boolean isAgressive;

    public boolean isAgressive() {
        return isAgressive;
    }

    public Enemy(String name, int health, Weapon weapon, Room currentRoom, boolean isAgressive) {
        this.name = name;
        this.health = health;
        this.equippedWeapon = weapon;
        this.currentRoom = currentRoom;
        this.isAgressive = isAgressive;
    }

    public void takeDamage(int damage){ // TODO: 11/10/2021 checks if dead
        health -= damage;
        if (!isAgressive){
            isAgressive = true;
        }
    }

    public Enum<StatusCode> attack(Character player) {
        if (equippedWeapon != null){
            return equippedWeapon.attack(player);
        } else {
            return StatusCode.NO_WEAPON_IN_SLOT;
        }
    }

    public void follow(String userInput){
        // get the chance for the enemy to follow
        double chance = Math.random();

        if (chance < 0.5){
            // gets the target room from the userInput, and save it in targetRoom
            Room targetRoom = currentRoom.getRoomFromDirectionName(userInput);

            if (targetRoom != null){
                currentRoom = targetRoom;
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public Item getWeapon() {
        return equippedWeapon;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
