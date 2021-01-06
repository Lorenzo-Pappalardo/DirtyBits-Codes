package threads_giving_numbers.v2;

abstract class Sample {
  private static int value = 0;

  private Sample() {
  }

  public static synchronized int set(int newValue) {
    int before = value;
    value = newValue;
    return before;
  }
}
