package it.isd.proxy;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface KeyValuePairsFile {
  Map<String, List<String>> getKeyValuePairs() throws IOException;
}
