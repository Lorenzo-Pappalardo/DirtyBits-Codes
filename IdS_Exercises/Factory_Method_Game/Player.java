package Factory_Method_Game;

public class Player {
    private static Player instance;
    String name;

    private Player(String name) {
        this.name = name;
    }

    public static Player getPlayerInstance(String name) {
        if (instance == null) {
            instance = new Player(name);
            System.out.print("\nProfile created!\n");
            return instance;
        }
        return instance;
    }

    public String getName() {
        return name;
    }
}