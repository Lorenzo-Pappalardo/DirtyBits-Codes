package prova_in_itinere_sockets_threads.threads;

public class MyThread extends Thread {
  protected SharedVariable variable = SharedVariable.getInstance();

  MyThread(String name) {
    super(name);
  }

  @Override
  public void run() {
    try {
      sleep(333);
    } catch (InterruptedException e) {
      e.printStackTrace();
      interrupt();
    }
  }
}
