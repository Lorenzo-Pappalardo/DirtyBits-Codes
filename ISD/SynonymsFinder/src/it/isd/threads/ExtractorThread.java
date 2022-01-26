package it.isd.threads;

import it.isd.KeyValue;

import java.util.List;
import java.util.concurrent.Callable;

public class ExtractorThread extends WorkerThread implements Callable<KeyValue<String, String>> {
  List<String> record;

  public ExtractorThread(String threadID, List<String> record) {
    super(threadID);
    this.record = record;
  }

  @Override
  public KeyValue<String, String> call() {
    String key = null;
    String value = null;

    if (record.size() == 1) {
      String[] tmp = record.get(0).split(",");
      key = tmp[1];
      value = tmp[5];
    }

    return new KeyValue<>(key, value);
  }
}
