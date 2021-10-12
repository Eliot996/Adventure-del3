import java.util.ArrayList;

public class Player extends Character {

    private int HP;
    private int maxHP = 50;
    private Room currentRoom;
    private int weight;
    private final int weightLimit = 25;
    private int energy = 100;
    private final ArrayList<Item> inventory = new ArrayList<>();

    public Player() {
        this.HP = maxHP;
        this.equippedWeapon = new MeleeWeapon("sword", "short sword", "a short sword", 5, 10);
    }

    public Enum<StatusCode> goTo(String userInput) {
        // gets the target room from the userInput, and save it in targetRoom
        Room targetRoom = currentRoom.getRoomFromDirectionName(userInput);

        // if the target room is not null, then set the currentRoom to targetRoom and return visitRoom.
        // else return error message
        if (targetRoom != null){
            currentRoom = targetRoom;
            return StatusCode.SUCCESS;
        }else{
            return StatusCode.DOES_NOT_EXIST;
        }
    }

    // returns a formatted string with info about the player
    public String getInfo() {
        return "Health: \t" + HP + "/" + maxHP + "\n" +
                "Weight: \t" + weight + "/" + weightLimit + "\n" +
                "Energy: \t" + energy + "/100\t";
    }

    public String getFormattedInventory() {
        if (inventory.size() > 0) {
            StringBuilder items = new StringBuilder("You have these items in your inventory:\n");

            for (Item item : inventory) {
                items.append(item.getLongName()).append("\n");
            }
            return items.toString();
        }
        return "You don't have any items in your inventory. Try picking something up with the 'take' command";
    }

    // drops item from user inventory and adds it to the current room, as well as subtracts the weight of the item from players weight
    public Enum<StatusCode> dropItem(Item item) {
        if (item != null) {
            inventory.remove(item);
            currentRoom.addItem(item);
            return StatusCode.SUCCESS;
        }
        return StatusCode.DOES_NOT_EXIST;
    }

    // Checks if item is in the current room, and then if the item will be too heavy in the inventory.
    public Enum<StatusCode> takeItem(Item item) {
        if (item != null) {
            if (weight + item.getWeight() <= weightLimit) {
                currentRoom.removeItem(item);
                inventory.add(item);
                weight += item.getWeight();
                return StatusCode.SUCCESS;
            } else {
                return StatusCode.FAIL;
            }
        } else {
            return StatusCode.DOES_NOT_EXIST;
        }
    }

    public Enum<StatusCode> eatItem(Item item) {
        //Checks if item is in the current room.
        if (item != null) {

            //checks if item has an item of type Food
            if (item instanceof Food) {
                HP += ((Food) item).getHealth();
                //Makes sure that player HP does not exceed maxHP.
                if (HP > maxHP) {
                    HP = 50;
                }
                inventory.remove(item);
                return StatusCode.SUCCESS;

            } else {
                return StatusCode.FAIL;
            }
        } else {
            return StatusCode.DOES_NOT_EXIST;
        }

    }

    public Item getItemFromName(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

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

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public String health() {
        return "Health: \t" + getHP() + "/" + getMaxHP();
    }

    //Updates energyUpdate with 25 points.
    public void takeABreak() {
        energyUpdate(25);
    }

    //Updates player energy
    public void energyUpdate(int energyUpdate) {
        int energy = getEnergy();
        energy += energyUpdate;
        //makes sure that energy does not exceed 100.
        if (energy > 100) {
            energy = 100;
        }
        setEnergy(energy);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Enum<StatusCode> equipWeapon(Item tmpItem) {
        // checks if there tmpItem contains an item
        if (tmpItem != null) {

            // checks if tmpItem has an item of type Weapon
            if (tmpItem instanceof Weapon) {

                // checks if equippedWeapon is empty
                if (equippedWeapon == null) {
                    // if the slot is empty, add tmpItem to equippedWeapon and remove it from the inventory,
                    // and return SUCCESS
                    equippedWeapon = (Weapon) tmpItem;
                    inventory.remove(tmpItem);

                    return StatusCode.SUCCESS;
                } else {
                    // if the slot is not empty, return SLOT_NOT_EMPTY
                    return StatusCode.SLOT_NOT_EMPTY;
                }
            } else {
                // if tmpItem is of wrong type return FAIL
                return StatusCode.FAIL;
            }
        } else {
            // if tmpItem is empty, return DOES_NOT_EXIST
            return StatusCode.DOES_NOT_EXIST;
        }
    }

    public Enum<StatusCode> unEquipWeapon() {
        if (equippedWeapon != null) {
            inventory.add(equippedWeapon);
            equippedWeapon = null;
            return StatusCode.SUCCESS;
        } else {
            return StatusCode.FAIL;
        }
    }

    @Override
    public Enum<StatusCode> takeDamage(int damage) {
        HP -= damage;

        if (HP <= 0){
            return StatusCode.DIED;
        } else {
            return StatusCode.SUCCESS;
        }
    }
}
