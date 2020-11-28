package Facade_PC_Booting_App;

import java.util.Random;

public class HDD {
    public byte[] read(int sector, int size) {
        System.out.println("Reading sector" + sector + " of size " + size);
        byte[] content = new byte[8];
        Random random = new Random();
        random.nextBytes(content);
        return content;
    }
}