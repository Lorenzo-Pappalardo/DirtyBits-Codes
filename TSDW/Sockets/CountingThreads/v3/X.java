package CountingThreads.v3;

public class X {
  private static X instance = null;
  private int value = 0;

  private X() {
  }

  public static X getInstance() {
    if (instance == null)
      instance = new X();
    return instance;
  }

  public synchronized int get() {
    return value;
  }

  public synchronized void increment() {
    value++;
  }
}
