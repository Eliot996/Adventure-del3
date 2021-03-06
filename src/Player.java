public class Player extends Character {

    private int weight;
    private final int weightLimit = 25;
    private int energy = 100;

    public Player() {
        maxHitPoints = 50;
        hitPoints = maxHitPoints;
        //this.equippedWeapon = new RangedWeapon("gun", "a gun", "this is a gun", 5, 10,5);
        //this.equippedShield = new Shield("shield", "a Shield", "this is a shield", 5, 60, 0.60);
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
        return "Health: \t" + hitPoints + "/" + maxHitPoints + "\n" +
                "Weight: \t" + weight + "/" + weightLimit + "\n" +
                "Energy: \t" + energy + "/100\t";
    }

    // returns a formatted string of the players inventory
    public String getFormattedInventory() {
        if (inventory.size() > 0) {
            StringBuilder items = new StringBuilder("You have these items in your inventory:\n");

            // add the long name of the given item to the stringbuilder along with a linebreak
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

    // attempts to eat the given item
    public Enum<StatusCode> eatItem(Item item) {
        //Checks if item is in the current room.
        if (item != null) {

            //checks if item has an item of type Food
            if (item instanceof Food) {
                hitPoints += ((Food) item).getHealth();
                //Makes sure that player HP does not exceed maxHP.
                if (hitPoints > maxHitPoints) {
                    hitPoints = 50;
                }
                // and removes item from the players inventory before returning success
                inventory.remove(item);
                return StatusCode.SUCCESS;

            } else {
                return StatusCode.FAIL;
            }
        } else {
            return StatusCode.DOES_NOT_EXIST;
        }

    }

    // iterates through the items in the players inventory, and check the item name against the given itenName
    public Item getItemFromName(String itemName) {
        for (Item item : inventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public String health() {
        return "Health: \t" + getHitPoints() + "/" + getMaxHitPoints();
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // attempts to equip the item as a weapon
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

    // attempts to equip the item as a shield
    public Enum<StatusCode> equipShield(Item tmpItem) {
        // checks if there tmpItem contains an item
        if (tmpItem != null) {

            // checks if tmpItem has an item of type Weapon
            if (tmpItem instanceof Shield) {

                // checks if equippedWeapon is empty
                if (equippedShield == null) {
                    // if the slot is empty, add tmpItem to equippedWeapon and remove it from the inventory,
                    // and return SUCCESS
                    equippedShield = (Shield) tmpItem;
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

    // attempts to unequip the weapon
    public Enum<StatusCode> unEquipWeapon() {
        if (equippedWeapon != null) {
            inventory.add(equippedWeapon);
            equippedWeapon = null;
            return StatusCode.SUCCESS;
        } else {
            return StatusCode.FAIL;
        }
    }

    // attempts to unequip the shield
    public Enum<StatusCode> unEquipShield() {
        if (equippedShield != null) {
            inventory.add(equippedShield);
            equippedShield = null;
            return StatusCode.SUCCESS;
        } else {
            return StatusCode.FAIL;
        }
    }
}
