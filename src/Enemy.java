public class Enemy {

    private String name;
    private int health;
    private Item weapon;
    private Room currentRoom;

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
