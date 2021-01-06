package CountingThreads.v2;

public class CountingThreads {
  public static int x = 0;

  public static synchronized int getX() {
    return x;
  }

  public static synchronized void incrementX() {
    x++;
  }

  public static void main(String[] args) {
    MyThread t1 = new MyThread("T1");
    MyThread t2 = new MyThread("T2");

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("All threads finished");
    System.out.println("X: " + x);
  }
}