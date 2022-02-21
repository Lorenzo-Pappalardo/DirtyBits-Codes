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

  private String removeStopWords(String original) {
    String[] wordsArray = original.split(" ");
    return Arrays.stream(wordsArray).filter(word -> !stopWords.contains(word.toLowerCase())).collect(Collectors.joining(" "));
  }

  private List<String> processColumns(String[] split) {
    List<String> columns = new ArrayList<>();

    boolean columnContinues = false;
    for (String column : split) {
      if (columnContinues) {
        int index = columns.size() - 1;
        columns.set(index, columns.get(index) + column);

        if (column.charAt(column.length() - 1) == '\"') {
          columnContinues = false;
        }
      } else {
        if (column.length() > 0 && column.charAt(0) == '\"') {
          columnContinues = true;
        }

        columns.add(column);
      }
    }

    return columns;
  }

  private void extractFromCSV(List<String> recordLines) {
    String key;
    String value = null;

    String[] split = recordLines.get(0).split(delimiter);
    List<String> columns = processColumns(split);

    key = columns.get(columnsToTake[0]);

    if (columns.size() > columnsToTake[1]) {
      String tmp = columns.get(columnsToTake[1]);

      tmp = tmp.replaceAll("[^\\w\\s]", "");

      value = removeStopWords(tmp);
    }

    if (value != null && value.length() > 0) {
      map.put(key, value);
    }
  }

  private void extractFromLEXICON(List<String> recordLines) {
    String key = null;
    String value;

    for (String line : recordLines) {
      if (line.contains("base")) {
        key = line.substring(6);
      } else if (line.contains("acronym") || line.contains("abbreviation")) {
        int startIndex = line.indexOf('=') + 1;

        value = line.substring(startIndex).replaceAll("\\|.*", "");

        value = removeStopWords(value);

        if (value != null && value.length() > 0) {
          map.put(key, value);
        }
      }
    }
  }

  /**
   * Extracts key-value pairs from a record in a file and adds them to a map
   *
   * @param recordLines List of strings representing a record in the original file
   */
  private void extractKeyValue(List<String> recordLines) {
    if (recordLines.size() == 1) {
      extractFromCSV(recordLines);
    } else {
      extractFromLEXICON(recordLines);
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

    System.out.println("Extracting pairs from: " + filePath);
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
