public class Food extends Item{
    private int health = 25;

    public Food(String shortName, String longName, String description, int weight, int health) {
        super(shortName, longName, description, weight);
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
