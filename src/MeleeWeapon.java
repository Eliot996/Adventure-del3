public class MeleeWeapon extends Weapon{

    private int damage;

    public MeleeWeapon(String shortName, String longName, String description, int weight, int damage) {
        super(shortName, longName, description, weight);
        this.damage = damage;
    }

    public Enum<StatusCode> attack(Character target){
        return target.takeDamage(damage);
    }

    @Override
    public String getDescription(){
        return super.getDescription() + '\n' +
                "Damage: " + damage  + '\n';
    }
}
