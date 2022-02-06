package it.isd.threads;

public abstract class WorkerThread {
  protected String threadID;

  protected WorkerThread(String threadID) {
    this.threadID = threadID;
  }
}
