package it.isd;

import it.isd.proxy.KeyValuePairsFile;
import it.isd.proxy.KeyValuePairsFileProxy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    try {
      final KeyValuePairsFile file = new KeyValuePairsFileProxy(Path.of("C:\\Users\\loren\\Desktop\\Progetto_ISD\\LEXICON"));
      final Map<String, List<String>> keyValuePairs;

      try {
        keyValuePairs = file.getKeyValuePairs();

        System.out.println(keyValuePairs);
      } catch (IOException e2) {
        System.err.println("Read error!");
      }
    } catch (IOException e) {
      System.err.println("Not a file!");
    }
  }
}
