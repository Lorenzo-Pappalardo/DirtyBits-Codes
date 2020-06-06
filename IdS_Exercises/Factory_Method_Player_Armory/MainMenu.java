package Factory_Method_Game;

import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
        System.out.println("Insert name: ");
        try {
            String name;
            Scanner s = new Scanner(System.in);
            name = s.next();
            s.close();

            final Player player = Player.getPlayerInstance(name);

            for (int i = 0; i < 10; i++) {
                Weapon weapon = Armory.getWeapon();
                System.out.println("Attempt N." + (i + 1) + " --> " + player.getName() + " gets a " + weapon.getName()
                        + ", AOE damage: " + weapon.getAOEDamage() + ", Single Hit Damage: "
                        + weapon.getSingleHitDamage());
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }
}