package Factory_Method_Player_Armory;

/** Product */
public interface Ammunitions {
    /**
     * Returns Ammunitions' name
     * 
     * @return type Ammunition's name
     */
    public String getName();

    /**
     * Returns Ammunitions' damage multiplier
     * 
     * @return multiplier Ammunitions' damage multiplier
     */
    public float getMultiplier();
}