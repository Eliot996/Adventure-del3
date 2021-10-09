import java.util.ArrayList;

public class Player {

    private int HP;
    private int maxHP = 50;
    private Room currentRoom;
    private int weight;
    private final int weightLimit = 25;
    private int energy = 100;
    private final ArrayList<Item> itemsInInventory = new ArrayList<>();
    private Weapon equippedWeapon;

    public Player() {
        this.HP = maxHP;
        this.equippedWeapon = new MeleeWeapon("sword", "short sword", "a short sword", 5, 10);
    }

    public String goTo(String userInput) {
        if ((userInput.equals("north") || userInput.equals("n")) && getCurrentRoom().hasNorth()) {
            currentRoom = currentRoom.getNorth();
            return currentRoom.visitRoom();
        }
        if ((userInput.equals("south") || userInput.equals("s")) && getCurrentRoom().hasSouth()) {
            currentRoom = currentRoom.getSouth();
            return currentRoom.visitRoom();
        }
        if ((userInput.equals("east") || userInput.equals("e")) && getCurrentRoom().hasEast()) {
            currentRoom = currentRoom.getEast();
            return currentRoom.visitRoom();
        }
        if ((userInput.equals("west") || userInput.equals("w")) && getCurrentRoom().hasWest()) {
            currentRoom = currentRoom.getWest();
            return currentRoom.visitRoom();
        }
        return "You cannot go that direction in this room";
    }

    // returns a formatted string with info about the player
    // TODO: add info to getInfo, add energy?
    public String getInfo() {
        return "Health: \t" + HP + "/" + maxHP + "\n" +
                "Weight: \t" + weight + "/" + weightLimit + "\n" +
                "Energy: \t" + energy + "/100\t";
    }

    public String getFormattedInventory() {
        if (itemsInInventory.size() > 0) {
            StringBuilder items = new StringBuilder("You have these items in your inventory:\n");

            for (Item item : itemsInInventory) {
                items.append(item.getLongName()).append("\n");
            }
            return items.toString();
        }
        return "You don't have any items in your inventory. Try picking something up with the 'take' command";
    }

    // drops item from user inventory and adds it to the current room, as well as subtracts the weight of the item from players weight
    public Enum<StatusCode> dropItem(Item item) {
        if (item != null){
            itemsInInventory.remove(item);
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
                itemsInInventory.add(item);
                weight += item.getWeight();
                return StatusCode.SUCCESS;
            } else {
              return StatusCode.FAIL;
            }
        } else {
            return StatusCode.DOES_NOT_EXIST;
        }
    }

    public Enum<StatusCode> eatItem(Item item){
        if(item != null) {

            if (item instanceof Food) {
                HP += ((Food) item).getHealth();
                if(HP > maxHP){
                    HP = 50;
                }
                itemsInInventory.remove(item);
                return StatusCode.SUCCESS;
                }else {
                return StatusCode.FAIL;
            }
        }else {
            return StatusCode.DOES_NOT_EXIST;
        }

    }

    public Item getItemFromName(String itemName) {
        for (Item item : itemsInInventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void attack(Enemy enemy){
        equippedWeapon.attack(enemy);
    }

    public ArrayList<Item> getItemsInInventory() {
        return itemsInInventory;
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
}
