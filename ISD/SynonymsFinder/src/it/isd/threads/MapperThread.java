package it.isd.threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class MapperThread extends WorkerThread implements Callable<Map<String, String>> {
  final Path filePath;
  final String delimiter;
  final int[] columnsToTake;
  final Map<String, String> map;
  final List<String> stopWords;

  public MapperThread(String threadID, Path filePath, String delimiter, int[] columnsToTake, List<String> stopWords) {
    super(threadID);
    this.filePath = filePath;
    this.delimiter = delimiter;
    this.columnsToTake = columnsToTake;
    this.stopWords = stopWords;
    map = new HashMap<>();
  }

  /**
   * Extracts key-value pairs from a record in a file and adds them to a map
   *
   * @param recordLines List of strings representing a record in the original file
   */
  private void extractKeyValue(List<String> recordLines) {
    String key = null;
    String value = null;

    if (recordLines.size() == 1) {
      String[] record = recordLines.get(0).split(delimiter);
      key = record[columnsToTake[0]];

      String tmp = record[columnsToTake[1]].toLowerCase();
      if (tmp.charAt(0) == '"') {
        tmp = tmp.substring(1);
      } else if (tmp.charAt(tmp.length() - 1) == '"') {
        tmp = tmp.substring(0, tmp.length() - 1);
      }

      tmp = tmp.replaceAll("[!().,;-]", "");

      String[] wordsArray = tmp.split(" ");
      value = Arrays.stream(wordsArray).filter(word -> !stopWords.contains(word)).collect(Collectors.joining(" "));


      if (!value.equals("")) {
        map.put(key, value);
      }
    } else {
      for (String line : recordLines) {
        if (line.contains("base")) {
          key = line.substring(6);
        } else if (line.contains("acronym") || line.contains("abbreviation")) {
          int startIndex = line.indexOf('=') + 1;
          value = "";

          for (int i = startIndex; i < line.length(); i++) {
            if (line.charAt(i) != '|') {
              value += line.charAt(i);
            } else {
              break;
            }
          }

          map.put(key, value);
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
