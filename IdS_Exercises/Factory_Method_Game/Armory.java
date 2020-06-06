package Factory_Method_Game;

import java.util.Random;

/** Creator */
public class Armory {
    /**
     * Return the instance of a Weapon
     * 
     * @return Weapon Instance of one of the subclasses of Weapon
     */
    public static Weapon getWeapon() {
        Random r = new Random(System.currentTimeMillis());
        if (r.nextInt() % 2 == 0)
            return new Pistol(getAmmunitions());
        else
            return new Shotgun(getAmmunitions());
    }

    /**
     * Return the instance of Ammunitions
     * 
     * @return Ammunitions Instance of a specific type of Ammunitions
     */
    public static Ammunitions getAmmunitions() {
        Random r = new Random(System.currentTimeMillis());
        if (r.nextInt() % 2 == 0)
            return new HeavyAmmunitions();
        else
            return new LightAmmunitions();
    }
}