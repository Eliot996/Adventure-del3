import java.util.ArrayList;

public class Player {

    private int HP;
    private int maxHP = 50;
    private Room currentRoom;
    private int weight;
    private final int weightLimit = 25;
    private int energy = 100;
    private final ArrayList<Item> itemsInInventory = new ArrayList<>();

    public Player() {
        this.HP = maxHP;

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
    public Item dropItem(String itemName) {
        for (Item item : itemsInInventory) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                itemsInInventory.remove(item);
                currentRoom.addItem(item);
                return item;
            }
        }
        return null;
    }

    // Checks if item is in the current room, and then if the item will be too heavy in the inventory.
    // 1: successful transfer
    // 0: item is to heavy
    // -1: item was no found
    public int takeItem(String itemName) {
        for (Item item : currentRoom.getItemsInRoom()) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                if (weight + item.getWeight() <= weightLimit) {
                    currentRoom.removeItem(item);
                    itemsInInventory.add(item);
                    weight += item.getWeight();
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return -1;
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
