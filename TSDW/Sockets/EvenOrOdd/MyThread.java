package EvenOrOdd;

import java.util.Random;

public class MyThread extends Thread {
  private int index;
  private int iterations;

  MyThread(String name, int index) {
    super(name);
    this.index = index;
    iterations = 0;
  }

  @Override
  public void run() {
    while (true) {
      try {
        sleep((index + 1) * 50l);
      } catch (InterruptedException e) {
        e.printStackTrace();
        interrupt();
      }

      Random random = new Random();
      int increment;
      while (true) {
        increment = random.nextInt(20);
        if (increment % 2 == index)
          break;
      }

      Main.updateN(increment);

      iterations++;

      if (index == 1) {
        System.out.println(getName() + " iterations: " + iterations);
        System.out.println(getName() + " reads n: " + Main.getN());
      } else {
        System.out.println("\t\t\t\t\t" + getName() + " iterations: " + iterations);
        System.out.println("\t\t\t\t\t" + getName() + " reads n: " + Main.getN());
      }

      if ((iterations >= 10 && Main.getN() % 2 == index) || (iterations >= 1000))
        break;
    }
  }
}
