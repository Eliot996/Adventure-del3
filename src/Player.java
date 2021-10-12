public class Player extends Character {

    private int weight;
    private final int weightLimit = 25;
    private int energy = 100;

    public Player() {
        maxHitPoints = 50;
        hitPoints = maxHitPoints;
        this.equippedWeapon = new RangedWeapon("gun", "a gun", "...", 5, 2,5);
        this.equippedShield = new Shield("shield", "a Shield", "this is a shield", 5, 60, 0.60);
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
                hitPoints += ((Food) item).getHealth();
                //Makes sure that player HP does not exceed maxHP.
                if (hitPoints > maxHitPoints) {
                    hitPoints = 50;
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
    public Enum<StatusCode> useAmmo(int ammo) {
        return null;
    }
}
