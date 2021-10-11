public class MeleeWeapon extends Weapon{

    private int damage;

    public MeleeWeapon(String shortName, String longName, String description, int weight, int damage) {
        super(shortName, longName, description, weight);
        this.damage = damage;
    }

    public void attack(Character target){
        target.takeDamage(damage);
    }
}
