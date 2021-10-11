public abstract class Character {
    protected Weapon equippedWeapon;

    public abstract void takeDamage(int damage);

    public abstract Enum<StatusCode> attack(Character target);
}
