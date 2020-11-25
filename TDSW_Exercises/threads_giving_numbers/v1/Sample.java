package threads_giving_numbers.v1;

public class Sample {
  private static Sample instance;
  private int value = 0;

  private Sample() {
  }

  public synchronized void set(int newValue) {
    value = newValue;
  }

  public synchronized int get() {
    return value;
  }

  public static Sample getInstance() {
    if (instance == null)
      instance = new Sample();
    return instance;
  }
}
