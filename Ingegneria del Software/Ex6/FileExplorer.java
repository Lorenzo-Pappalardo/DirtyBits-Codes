package Ex6;

import java.io.File;
import java.util.List;
import java.nio.file.FileSystems;
import java.nio.file.Files;

public class FileExplorer {
    public FileExplorer(String path) {
        printPathRecursively(new File(path), 0);
    }

    private void indent(int indent_depth) {
        for (int i = 0; i < indent_depth; i++) {
            System.out.print('\t');
        }
    }

    public void printPathRecursively(File f, int indent_depth) {
        if (f.isDirectory()) {
            indent(indent_depth);

            System.out.println("--> " + f.getName() + " is a directory, printing contents below...");

            for (File tmp : f.listFiles()) {
                printPathRecursively(tmp, indent_depth + 1);
            }
        } else
            printRegularFile(f, indent_depth);
    }

    private void printRegularFile(File f, int indent_depth) {
        indent(indent_depth);

        System.out.println("--> " + f.getName() + " is a regular file, printing content below...");

        try {
            List<String> lines = Files.readAllLines(FileSystems.getDefault().getPath(f.getPath()));

            for (String tmp : lines) {
                indent(indent_depth + 1);
                System.out.println(tmp);
            }
        } catch (Exception e) {
            System.err.println(e);
            System.exit(3);
        }
    }
}