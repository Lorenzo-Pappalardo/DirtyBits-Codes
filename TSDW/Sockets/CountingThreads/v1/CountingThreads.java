package CountingThreads.v1;

public class CountingThreads {
  private static int x = 0;

  public static synchronized int incrementCount() {
    return ++x;
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