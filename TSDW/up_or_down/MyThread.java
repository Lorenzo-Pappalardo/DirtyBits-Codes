package up_or_down;

public class MyThread {
  protected final String name;
  protected int iterations;
  protected N n;

  protected MyThread(String name) {
    this.name = name;
    iterations = 0;
    n = N.getInstance();
  }

  protected String getName() {
    return name;
  }
}