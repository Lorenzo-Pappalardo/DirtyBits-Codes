package it.isd;

import it.isd.threads.ExtractorThread;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class KeyValueExtractor {
  Path filePath;
  String delimiter;

  KeyValueExtractor(Path filePath, String delimiter) {
    this.filePath = filePath;
    this.delimiter = delimiter;
  }

  public Map<String, String> getKeyValue() {
    Map<String, String> map = new HashMap<>();
    List<Future<KeyValue<String, String>>> futures = new ArrayList<>();

    ExecutorService executor = Executors.newCachedThreadPool();

    try {
      LineNumberReader reader = new LineNumberReader(new FileReader(filePath.toFile()));
      int recordsCount = 0;

      while (reader.ready()) {
        List<String> tmpRecord = new ArrayList<>();

        String line;
        while (!(line = reader.readLine()).contains(delimiter)) {
          tmpRecord.add(line);
        }

        tmpRecord.add(line);

        recordsCount++;
        futures.add(executor.submit(new ExtractorThread("ET" + recordsCount, tmpRecord)));
      }
    } catch (IOException e) {
      System.err.println("Error encountered while reading the file: " + filePath);
    }

    executor.shutdown();

    futures.forEach(future -> {
      try {
        map.put(future.get().key, future.get().value);
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }
    });

    return map;
  }
}
