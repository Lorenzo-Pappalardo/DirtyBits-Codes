/*
    3) Scrivere un metodo che inizializzi e ritorni una lista di array di float.
*/

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Ex3 {
    private static List<float[]> listofarrays;

    public static void main(String args[]) {
        if (args.length < 1) {
            System.err.println("You have to pass a length for the new array");
            System.exit(1);
        }

        listofarrays = createList(Integer.parseInt(args[0]));

        for (float[] tmp : listofarrays) {
            System.out.println(Arrays.toString(tmp));
        }
    }

    private static List<float[]> createList(int dim) {
        float[] arr1 = new float[dim];
        float[] arr2 = new float[dim];
        float[] arr3 = new float[dim];

        Random random = new Random();

        for (int i = 0; i < dim; i++) {
            arr1[i] = random.nextFloat() * random.nextInt(100);
            arr2[i] = random.nextFloat() * random.nextInt(100);
            arr3[i] = random.nextFloat() * random.nextInt(100);
        }

        List<float[]> listofarrays = new ArrayList<>();

        listofarrays.add(arr1);
        listofarrays.add(arr2);
        listofarrays.add(arr3);

        return listofarrays;
    }
}