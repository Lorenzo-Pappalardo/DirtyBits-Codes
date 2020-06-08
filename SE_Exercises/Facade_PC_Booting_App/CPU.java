package Facade_PC_Booting_App;

public class CPU {
    /** Loads the content at the specified memory address into a register */
    public String loadInRegister(int address) {
        System.out.println("Loaded content of memory address " + address + " in register EAX");
        return "EAX";
    }

    public void exec(String register) {
        System.out.println("Executing content of " + register);
    }
}