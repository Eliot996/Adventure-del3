public class Shield extends Item{
    private int durability;
    private final double blockEffect;

    public Shield(String shortName, String longName, String description, int weight, int durability, double blockEffect) {
        super(shortName, longName, description, weight);
        this.durability = durability;
        this.blockEffect = blockEffect;
    }

    public int block(int damage){
        if (durability > 0) {
            int damageBlocked = (int) Math.ceil(blockEffect * damage);
            durability -= damageBlocked;
            return damageBlocked;
        } else {
            System.out.println("Your shield is broken, and did not block any damage"); // TODO: bad practice, but can see another way, as of now
            return 0;
        }
    }

    public int getDurability() {
        return durability;
    }

    public double getBlockEffect() {
        return blockEffect;
    }
}
