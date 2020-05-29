package Factory_Method_Game;

import java.util.Random;

public class Armory {
    public static Weapon getWeapon() {
        Random r = new Random();
        if (r.nextInt() % 2 == 0)
            return new Pistol(getAmmunitions());
        else
            return new Shotgun(getAmmunitions());
    }

    public static Ammunitions getAmmunitions() {
        Random r = new Random();
        if (r.nextInt() % 2 == 0)
            return new HeavyAmmunitions();
        else
            return new LightAmmunitions();
    }
}