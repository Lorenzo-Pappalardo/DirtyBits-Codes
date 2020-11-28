package up_or_down;

public class N {
  private static N instance;

  private N() {
  }

  private int value = 100;

  public synchronized int get() {
    return value;
  }

  public synchronized void increase(int newValue) {
    value += newValue;
  }

  public synchronized void decrease(int newValue) {
    value -= newValue;
  }

  public static N getInstance() {
    if (instance == null)
      instance = new N();
    return instance;
  }
}
