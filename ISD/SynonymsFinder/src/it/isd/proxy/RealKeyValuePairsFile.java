package it.isd.proxy;

import it.isd.Splitter;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RealKeyValuePairsFile implements KeyValuePairsFile {
  final Path pathToFile;
  final Map<String, List<String>> keyValuePairsMap = new HashMap<>();

  RealKeyValuePairsFile(Path pathToFile) {
    this.pathToFile = pathToFile;
  }

  private int megabytesToBytes(int megabytes) {
    return megabytes * 1048576;
  }

  @Override
  public Map<String, List<String>> getKeyValuePairs() throws IOException {
    Splitter splitter = new Splitter(pathToFile, megabytesToBytes(1));
    splitter.splitFile();
    return keyValuePairsMap;
  }
}
