public class Item {
    private final String shortName;
    private final String longName;
    private final String description;
    private int weight;

    public Item(String shortName, String longName, String description, int weight) {
        this.shortName = shortName;
        this.longName = longName;
        this.description = description;
        this.weight = weight;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLongName() {
        return longName;
    }

    public String getDescription() {
        return description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
