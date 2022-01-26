package it.isd.threads;

import java.nio.file.Path;

public class ReaderThread extends WorkerThread implements Runnable {
  final Path filePath;

  public ReaderThread(String threadID, Path filePath) {
    super(threadID);
    this.filePath = filePath;
  }

  @Override
  public void run() {
    System.out.println('T' + threadID + " started...");

    System.out.println('T' + threadID + " finished.");
  }
}
