public class RangedWeapon extends Weapon{

    private int ammo;
    private int damage;


    public RangedWeapon(String shortName, String longName, String description, int weight, int ammo, int damage) {
        super(shortName, longName, description, weight);
        this.ammo = ammo;
        this.damage = damage;
    }

    public Enum<StatusCode> attack(Character target){
        if(ammo > 0) {
            ammo--;
            return target.takeDamage(damage);
        }else

        return StatusCode.NO_AMMO_LEFT;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
