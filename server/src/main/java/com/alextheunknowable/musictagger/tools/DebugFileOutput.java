package com.alextheunknowable.musictagger.tools;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Stream;

public class DebugFileOutput {
    public static void main(String[] args) {
        // List of paths: can be individual files or folders
        List<String> pathsToInclude = List.of(
                "src/main/java/com/alextheunknowable/musictagger/model/Track",
                "src/main/java/com/alextheunknowable/musictagger/model/TrackDto"
        );

        Path outputFile = Paths.get("selected_code.txt");
        StringBuilder allCode = new StringBuilder();

        for (String pathStr : pathsToInclude) {
            Path path = Paths.get(pathStr);

            if (Files.exists(path)) {
                if (Files.isDirectory(path)) {
                    // Include all .java files in this folder (non-recursive, can add recursion if needed)
                    try (Stream<Path> files = Files.list(path)) {
                        files.filter(Files::isRegularFile)
                                .filter(p -> p.toString().endsWith(".java"))
                                .forEach(p -> appendFile(p, allCode));
                    } catch (IOException e) {
                        System.err.println("Error reading folder: " + path);
                        e.printStackTrace();
                    }
                } else {
                    appendFile(path, allCode);
                }
            } else {
                System.err.println("Path does not exist: " + path);
            }
        }

        try {
            Files.writeString(outputFile, allCode.toString());
            System.out.println("Selected Java code written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void appendFile(Path file, StringBuilder allCode) {
        try {
            allCode.append("// File: ").append(file.toString()).append("\n");
            allCode.append(Files.readString(file)).append("\n\n");
        } catch (IOException e) {
            System.err.println("Error reading file: " + file);
            e.printStackTrace();
        }
    }
}
