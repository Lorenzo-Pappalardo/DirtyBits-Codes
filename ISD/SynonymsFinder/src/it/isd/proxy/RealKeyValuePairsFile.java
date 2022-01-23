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

  @Override
  public Map<String, List<String>> getKeyValuePairs() throws IOException {
    Splitter splitter = new Splitter(pathToFile, 1000);
    List<Path> newFilesNames = splitter.splitFile();
    return keyValuePairsMap;
  }
}
