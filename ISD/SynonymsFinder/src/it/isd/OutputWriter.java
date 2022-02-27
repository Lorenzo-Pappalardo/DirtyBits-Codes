package it.isd;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OutputWriter {
  private FileChannel outputFile = null;

  OutputWriter(Path outputFilePath) {
    try {
      if (!Files.exists(outputFilePath.getParent())) {
        Files.createDirectory(outputFilePath.getParent());
      }

      this.outputFile = FileChannel.open(outputFilePath, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    } catch (IOException e) {
      System.err.println("Unable to open output file");
    }
  }

  /**
   * Writes a Map to an output file
   *
   * @param map {Map} Map to be written
   */
  public void writeMapToFile(Map<String, String> map) {
    List<Map.Entry<String, String>> ordered = map.entrySet().stream().sorted(Map.Entry.<String, String>comparingByValue().reversed()).toList();

    ordered.forEach(entry -> {
      try {
        Optional<String> values = Arrays.stream((entry.getValue().split(" "))).reduce((res, value) -> res += ", " + value);
        outputFile.write(ByteBuffer.wrap((entry.getKey() + ", " + (values.orElse("")) + "\n").getBytes(StandardCharsets.UTF_8)));
      } catch (IOException e) {
        System.err.println("Error writing in output file");
      }
    });

    try {
      outputFile.close();
    } catch (IOException e) {
      System.err.println("Error closing the output file");
    }
  }
}
