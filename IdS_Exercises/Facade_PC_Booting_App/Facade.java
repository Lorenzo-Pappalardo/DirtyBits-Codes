package Facade_PC_Booting_App;

public class Facade {
    private final int BOOT_ADDRESS = 0x1024;
    private final int BOOT_SECTOR = 1;
    private final int SECTOR_SIZE = 512;

    private CPU cpu;
    private Memory memory;
    private HDD hdd;

    public Facade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hdd = new HDD();
    }

    /** Starting the PC */
    public void start() {
        /** Accessing HDD and reading bytes */
        byte[] bytesRead = hdd.read(BOOT_SECTOR, SECTOR_SIZE);

        /** Loading bytes read in memory */
        memory.load(BOOT_ADDRESS, bytesRead);

        /** First command executed */
        cpu.exec(cpu.loadInRegister(BOOT_ADDRESS));
    }
}