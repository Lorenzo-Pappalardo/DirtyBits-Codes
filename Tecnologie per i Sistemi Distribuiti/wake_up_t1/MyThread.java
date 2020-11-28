package wake_up_t1;

import java.util.Random;

public class MyThread extends Thread {
  public static Boolean stopT2 = false;

  MyThread(String name) {
    super(name);
  }

  @Override
  public void run() {
    while (true) {
      if (getName().equals("T1")) {
        try {
          sleep(100);
        } catch (Exception e) {
          e.printStackTrace();
        }

        int newValue = new Random().nextInt(11);

        if (newValue == X.get()) {
          System.out.println("Right answer");
          break;
        } else
          System.out.println("Wrong answer");

        if (Math.abs(newValue - X.get()) >= 4) {
          System.out.println("Sleeping");
        }

        synchronized (X.lock) {
          try {
            X.lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
            interrupt();
          }
        }

        if (Math.abs(newValue - X.get()) >= 4) {
          System.out.println("Alright man, chill...");
        }
      } else {
        try {
          sleep(300);
        } catch (Exception e) {
          e.printStackTrace();
        }

        if (!stopT2) {
          synchronized (X.lock) {
            X.lock.notifyAll();
          }

          System.out.println("WAKE THE F**K UP T1!");
        } else
          break;
      }
    }
  }
}
