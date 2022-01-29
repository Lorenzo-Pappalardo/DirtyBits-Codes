package it.isd.threads;

import it.isd.KeyValue;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class MapperThread implements Callable<Map<String, String>> {
  Path filePath;
  String delimiter;
  final Map<String, String> map;

  public MapperThread(Path filePath, String delimiter) {
    this.filePath = filePath;
    this.delimiter = delimiter;
    map = new HashMap<>();
  }

  @Override
  public Map<String, String> call() {
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
