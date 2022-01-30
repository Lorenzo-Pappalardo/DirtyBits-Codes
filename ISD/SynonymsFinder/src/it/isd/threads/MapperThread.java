package it.isd.threads;

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

  private void extractKeyValue(List<String> record) {
    String key;
    String value = null;

    if (record.size() == 1) {
      String[] tmp = record.get(0).split(",");
      key = tmp[1];
      if (tmp.length >= 6) {
        value = tmp[5];
      }

      map.put(key, value);
    } else {
      for (String line : record) {
        if (line.contains("base")) {
          key = line.substring(6);

          map.put(key, null);
        } else if (line.contains("acronym") || line.contains("abbreviation")) {
          int startIndex = 0;
          key = "";

          if (line.contains("acronym")) {
            startIndex = 12;
          } else if (line.contains("abbreviation")) {
            startIndex = 17;
          }

          for (int i = startIndex; i < line.length(); i++) {
            if (line.charAt(i) != '|') {
              key += line.charAt(i);
            } else {
              break;
            }
          }

          map.put(key, null);
        }
      }
    }
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

        extractKeyValue(tmpRecord);
      }
    } catch (IOException e) {
      System.err.println("MT_" + threadID + ": Error in reading the file.");
      System.exit(1);
    }

    return map;
  }
}
