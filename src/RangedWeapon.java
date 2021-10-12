public class RangedWeapon extends Weapon{

    private int ammo;


    public RangedWeapon(String shortName, String longName, String description, int weight, int ammo) {
        super(shortName, longName, description, weight);
        this.ammo = ammo;
    }

    public Enum<StatusCode> attack(Character target){
        return StatusCode.FAIL;
    }

    @Override
    public int ammoLeft() {
        return ammo;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
