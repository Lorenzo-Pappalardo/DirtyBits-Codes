package it.isd;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Splitter {
  final Path pathToOriginalFile;
  final Path pathToOriginalFileDirectory;
  final String delimiter;
  final int maxRecordsPerFile;
  final Path partialsDirectory;
  final List<Path> newFilesPath;

  public Splitter(Path pathToOriginalFile, String delimiter, int maxRecordsPerFile) {
    this.pathToOriginalFile = pathToOriginalFile;
    this.pathToOriginalFileDirectory = pathToOriginalFile.getParent().toAbsolutePath();
    this.delimiter = delimiter;
    this.maxRecordsPerFile = maxRecordsPerFile;
    this.partialsDirectory = Path.of(pathToOriginalFileDirectory + "\\Partials");
    this.newFilesPath = new ArrayList<>();
  }

  /**
   * Creates a new partial file with the specified id
   *
   * @param id ID to assign to the created file
   * @return {@link Path} Path to the newly created file
   */
  private Path createNewPartialFile(int id) {
    if (!Files.exists(partialsDirectory)) {
      try {
        Files.createDirectory(partialsDirectory);
      } catch (IOException e) {
        System.err.println("Error encountered while creating the partials directory");
        System.exit(1);
      }
    }
    Path tmpPath = null;
    final int dotIndex = pathToOriginalFile.getFileName().toString().lastIndexOf(".");
    if (dotIndex != -1) {
      final String fileNameWithoutExtension = pathToOriginalFile.getFileName().toString().substring(0, dotIndex);
      final String fileExtension = pathToOriginalFile.getFileName().toString().substring(dotIndex);
      tmpPath = Path.of(partialsDirectory + "\\" + fileNameWithoutExtension + '_' + id + fileExtension);
    } else {
      tmpPath = Path.of(partialsDirectory + "\\" + pathToOriginalFile.getFileName() + '_' + id);
    }

    try {
      Files.createFile(tmpPath);
    } catch (FileAlreadyExistsException ignored) {
    } catch (IOException e) {
      System.err.println("Error encountered while creating the partial file " + tmpPath);
      System.exit(2);
    }

    return tmpPath;
  }

  /**
   * Reads a big file and splits its content across multiple partial files
   *
   * @return {@link List<Path>} List of Paths to the newly created partial files
   */
  public List<Path> splitFile() {
    try (LineNumberReader reader = new LineNumberReader(new FileReader(pathToOriginalFile.toString()))) {
      int newFilesIndex = 0;

      while (reader.ready()) {
        newFilesPath.add(createNewPartialFile(newFilesIndex + 1));
        FileChannel outputFile = FileChannel.open(newFilesPath.get(newFilesIndex), StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        List<String> buffer = new ArrayList<>();

        int recordsRead = 0;
        while (recordsRead < maxRecordsPerFile) {
          final String line = reader.readLine();
          if (line == null) {
            break;
          }

          if (line.contains(delimiter)) {
            recordsRead++;
          }

          buffer.add(line);
        }

        outputFile.write(ByteBuffer.wrap(String.join("\n", buffer).getBytes(StandardCharsets.UTF_8)));
        outputFile.close();

        newFilesIndex++;
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found");
      System.exit(3);
    } catch (IOException e) {
      System.err.println("Error encountered while splitting the file: " + pathToOriginalFile);
    }

    return newFilesPath;
  }
}
