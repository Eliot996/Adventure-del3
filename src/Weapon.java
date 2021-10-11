public abstract class  Weapon extends Item{


    public Weapon(String shortName, String longName, String description, int weight) {
        super(shortName, longName, description, weight);
    }

    public abstract Enum<StatusCode> attack(Character taget);
}
