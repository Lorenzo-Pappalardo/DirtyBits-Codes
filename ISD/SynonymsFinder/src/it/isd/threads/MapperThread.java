package it.isd.threads;

import it.isd.KeyValue;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class MapperThread extends WorkerThread implements Callable<Map<String, String>> {
  Path filePath;
  String delimiter;
  final Map<String, String> map;

  public MapperThread(String threadID, Path filePath, String delimiter) {
    super(threadID);
    this.filePath = filePath;
    this.delimiter = delimiter;
    map = new HashMap<>();
  }

  private KeyValue<String, String> extractKeyValue(List<String> record) {
    String key = null;
    String value = null;

    if (record.size() == 1) {
      String[] tmp = record.get(0).split(",");
      key = tmp[1];
      if (tmp.length >= 6) {
        value = tmp[5];
      }
    } else {
      for (String line : record) {
        if (line.contains("base")) {
          key = line.substring(6);
          break;
        }
      }
    }

    return new KeyValue<>(key, value);
  }

  @Override
  public Map<String, String> call() {
    LineNumberReader reader = null;
    try {
      reader = new LineNumberReader(new FileReader(filePath.toFile()));
    } catch (FileNotFoundException e) {
      System.err.println("MT_" + threadID + ": File not found.");
      System.exit(1);
    }

    try {
      while (reader.ready()) {
        List<String> tmpRecord = new ArrayList<>();

        String line;
        while (!(line = reader.readLine()).contains(delimiter)) {
          tmpRecord.add(line);
        }
        tmpRecord.add(line);

        KeyValue<String, String> kv = extractKeyValue(tmpRecord);
        map.put(kv.key, kv.value);
      }
    } catch (IOException e) {
      System.err.println("MT_" + threadID + ": Error in reading the file.");
      System.exit(1);
    }

    return map;
  }
}
