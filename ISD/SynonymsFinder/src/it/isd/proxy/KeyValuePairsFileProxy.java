package it.isd.proxy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class KeyValuePairsFileProxy implements KeyValuePairsFile {
  RealKeyValuePairsFile file;

  public KeyValuePairsFileProxy(Path pathToFile) throws IOException {
    if (Files.notExists(pathToFile) || Files.isDirectory(pathToFile)) {
      throw new IOException("Not a file!");
    }

    file = new RealKeyValuePairsFile(pathToFile);
  }

  @Override
  public Map<String, List<String>> getKeyValuePairs() throws IOException {
    return file.getKeyValuePairs();
  }
}
