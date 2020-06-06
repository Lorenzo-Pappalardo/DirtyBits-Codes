package Factory_Method_Player_Armory;

/** Product */
public interface Weapon {
    /**
     * Returns Weapon's name
     * @return name Weapon's name
     */
    public String getName();

    /**
     * Returns AOE Damage
     * @return AOEDamage A float which indicates the amount of damage per AOE
     */
    public float getAOEDamage();

    /**
     * Returns Single Hit Damage
     * @return SingleHitDamage   A float that indicates the amount of damage per single hit
     */
    public float getSingleHitDamage();
}