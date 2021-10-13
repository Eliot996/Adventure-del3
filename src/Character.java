import java.util.ArrayList;

public abstract class Character {

    protected Room currentRoom;
    protected Weapon equippedWeapon;
    protected Shield equippedShield;
    protected int hitPoints;
    protected int maxHitPoints;
    protected final ArrayList<Item> inventory = new ArrayList<>();

    // attempts to attack the given target
    public Enum<StatusCode> attack(Character target) {
        if (target != null) {
            if (equippedWeapon != null) {
                return equippedWeapon.attack(target);
            } else {
                return StatusCode.NO_WEAPON_IN_SLOT;
            }
        } else {
            return StatusCode.DOES_NOT_EXIST;
        }
    }

    public Enum<StatusCode> takeDamage(int damage) {
        // if the character has a shield the use it to block some of the incoming damage
        if (equippedShield != null){
            hitPoints -= (damage - equippedShield.block(damage));
        } else {
            hitPoints -= damage;
        }

        // check if dead, else return success
        if (hitPoints <= 0){
            return StatusCode.DIED;
        } else {
            return StatusCode.SUCCESS;
        }
    }


    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
