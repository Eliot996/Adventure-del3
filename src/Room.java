import java.util.ArrayList;

public class Room {

    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private final String name;
    private final String description;
    private boolean hasBeenvisited;
    private final ArrayList<Item> itemsInRoom = new ArrayList<>();

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // adds the given item to itemsInRoom
    public Item addItem(Item item) {
        itemsInRoom.add(item);
        return item;
    }

    // removes the given item from itemsInRoom
    public Item removeItem(Item item) {
        if (itemsInRoom.contains(item)) {
            itemsInRoom.remove(item);
            return item;
        }
        return null;
    }

    // takes a string to match with the name of the direction,
    // and if the room has a connection that way, it will return the room
    public Room getRoomFromDirectionName(String userInput){
        if ((userInput.equals("north") || userInput.equals("n")) && hasNorth()) {
            return getNorth();
        }
        if ((userInput.equals("south") || userInput.equals("s")) && hasSouth()) {
            return getSouth();
        }
        if ((userInput.equals("east") || userInput.equals("e")) && hasEast()) {
            return getEast();
        }
        if ((userInput.equals("west") || userInput.equals("w")) && hasWest()) {
            return getWest();
        }
        return null;
    }

    // will return a formatted String of the items in the room
    public String getItems() {
        if (itemsInRoom.size() > 0) {
            StringBuilder items = new StringBuilder("You found these items:\n");

            for (Item item : itemsInRoom) {
                items.append(item.getLongName()).append("\n");
            }
            return items.toString();
        }
        return "You cannot seem to find any items in this room";
    }

    public Item getItemFromName(String itemName) {
        for (Item item : itemsInRoom) {
            if (item.getShortName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Item> getItemsInRoom() {
        return itemsInRoom;
    }

    public Room getNorth() {
        return north;
    }

    public Room getSouth() {
        return south;
    }

    public Room getWest() {
        return west;
    }

    public Room getEast() {
        return east;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // returns the name of the room, and if the player has not visited the room before, also the description
    public String visitRoom() {
        StringBuilder returnString = new StringBuilder(name + "\n");

        if (!hasBeenvisited) {
            returnString.append(description);
            hasBeenvisited = true;
        }

        return returnString.toString();
    }

    public void setNorth(Room north) {
        if (this.north == null) {
            this.north = north;
            north.setSouth(this);
        }
    }

    public void setSouth(Room south) {
        if (this.south == null) {
            this.south = south;
            south.setNorth(this);
        }
    }

    public void setWest(Room west) {
        if (this.west == null) {
            this.west = west;
            west.setEast(this);
        }
    }

    public void setEast(Room east) {
        if (this.east == null) {
            this.east = east;
            east.setWest(this);
        }
    }

    public boolean hasNorth() {
        return north != null;
    }

    public boolean hasSouth() {
        return south != null;
    }

    public boolean hasEast() {
        return east != null;
    }

    public boolean hasWest() {
        return west != null;
    }
}
