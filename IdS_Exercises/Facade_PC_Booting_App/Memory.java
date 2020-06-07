package Facade_PC_Booting_App;

public class Memory {
    /** Loads a content in memory starting from the specified address */
    public void load(int address, byte[] content) {
        System.out.println(content + " loaded in memory, starting from address " + address);
    }
}