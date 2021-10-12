public abstract class Character {
    protected Weapon equippedWeapon;

    public abstract Enum<StatusCode> takeDamage(int damage);

    public abstract Enum<StatusCode> attack(Character target);
}
