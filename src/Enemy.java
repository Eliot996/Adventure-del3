import java.util.ArrayList;

public class Enemy extends Character{

    // TODO: 08/10/2021 add functionality to Class, should the enemy move around?

    private String name;
    private boolean isAgressive;

    public boolean isAgressive() {
        return isAgressive;
    }

    public Enemy(String name, int hitPoints, Weapon weapon, Room currentRoom, boolean isAgressive) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.equippedWeapon = weapon;
        this.currentRoom = currentRoom;
        this.isAgressive = isAgressive;
    }

    @Override
    public Enum<StatusCode> takeDamage(int damage){
        // checks if the enemy is aggressive, if not then set to be aggressicve
        if (!isAgressive){
            isAgressive = true;
        }
        return super.takeDamage(damage);
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

    public void die(){
        currentRoom.addItem(equippedWeapon);
        for (Item item: inventory) {
            currentRoom.addItem(item);
        }
    }

    public String getName() {
        return name;
    }

    public Item getWeapon() {
        return equippedWeapon;
    }
}
