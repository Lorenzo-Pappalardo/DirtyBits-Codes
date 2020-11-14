package CountingThreads;

public class CountingThreads {
  private static int x = 0;

  public static synchronized int getX() {
    return x;
  }

  public static synchronized void incrementX() {
    x++;
  }

  public static void main(String[] args) {
    MyThread t1 = new MyThread();
    MyThread t2 = new MyThread();

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();

      System.out.println("X value: " + x);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}