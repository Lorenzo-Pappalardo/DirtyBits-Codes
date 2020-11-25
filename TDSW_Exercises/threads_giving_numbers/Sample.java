package threads_giving_numbers;

public class Sample {
  private int value = 0;

  public synchronized void set(int newValue) {
    value = newValue;
  }

  public synchronized int get() {
    return value;
  }
}
