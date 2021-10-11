public class RangedWeapon extends Weapon{


    public RangedWeapon(String shortName, String longName, String description, int weight) {
        super(shortName, longName, description, weight);
    }

    public Enum<StatusCode> attack(Character target){
        return StatusCode.FAIL;
    }

}
