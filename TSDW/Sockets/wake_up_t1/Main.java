package wake_up_t1;

public class Main {
  public static void main(String[] args) {
    MyThread t1 = new MyThread("T1");
    MyThread t2 = new MyThread("T2");

    t1.start();
    t2.start();

    try {
      t1.join();
      MyThread.stopT2 = true;
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }

    System.out.println("All finished");
  }
}