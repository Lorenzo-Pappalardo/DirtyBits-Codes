package CountingThreads;

import java.io.FileWriter;
import java.io.IOException;

public class Test {
  private static Integer success = 0;

  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      CountingThreads.main(args);
      if (CountingThreads.getX() == 300)
        success++;
    }

    try (FileWriter file = new FileWriter("res.txt");) {
      file.write(success.toString());
      file.write('%');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
