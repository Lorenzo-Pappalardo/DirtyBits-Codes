package Factory_Method_Game;

/** Singleton */
public class Player {
    private static Player instance;
    String name;

    private Player(String name) {
        this.name = name;
    }

    /**
     * Returns the only instance of class Player
     * @param name  Name to give to the Player
     * @return Player's instance
     */
    public static Player getPlayerInstance(String name) {
        if (instance == null) {
            instance = new Player(name);
            System.out.print("\nProfile created!\n");
            return instance;
        }
        return instance;
    }

    /**
     * Return Player's name
     * @return name Player's name
     */
    public String getName() {
        return name;
    }
}