public class RangedWeapon extends Weapon{

    private int ammo;
    private final int maxAmmo;
    private final int damage;


    public RangedWeapon(String shortName, String longName, String description, int weight, int ammo, int damage) {
        super(shortName, longName, description, weight);
        this.maxAmmo = ammo;
        this.ammo = this.maxAmmo;
        this.damage = damage;
    }

    // attacks if there is ammo left in the weapon, else returns no ammo left
    public Enum<StatusCode> attack(Character target){
        if(ammo > 0) {
            ammo--;
            return target.takeDamage(damage);
        }

        return StatusCode.NO_AMMO_LEFT;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    @Override
    public String getDescription(){
        return super.getDescription() + '\n' +
                "Damage: " + damage  + '\n' +
                "Ammo: " + ammo + '/' + maxAmmo;
    }
}
