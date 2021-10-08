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

    public Item addItem(Item item) {
        itemsInRoom.add(item);
        return item;
    }

    public Item removeItem(Item item) {
        if (itemsInRoom.contains(item)) {
            itemsInRoom.remove(item);
            return item;
        }
        return null;
    }

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

    public String visitRoom() {
        StringBuilder returnString = new StringBuilder(name + "\n");

        if (!hasBeenvisited) {
            returnString.append(description).append("\n");
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
