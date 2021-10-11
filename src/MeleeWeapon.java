public class MeleeWeapon extends Weapon{

    private int damage;

    public MeleeWeapon(String shortName, String longName, String description, int weight, int damage) {
        super(shortName, longName, description, weight);
        this.damage = damage;
    }

    public Enum<StatusCode> attack(Character target){
        target.takeDamage(damage);
        return StatusCode.SUCCESS;
    }
}
