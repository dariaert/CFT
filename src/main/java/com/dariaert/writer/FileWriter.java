package com.dariaert.writer;

import com.dariaert.classifier.DataType;
import com.dariaert.config.Config;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

public class FileWriter {

    private final Config config;
    private final Map<DataType, BufferedWriter> writers = new HashMap<>();
    private boolean outputAvailable = true;

    public FileWriter(Config config) {
        this.config = config;
        initOutputDirectory();
    }

    private void initOutputDirectory() {
        Path outputDir = Path.of(config.outputPath);

        try {
            if (Files.notExists(outputDir)) {
                Files.createDirectories(outputDir);
            }
        } catch (IOException e) {
            System.err.println(
                    "Ошибка: не удалось создать директорию вывода: " + config.outputPath
            );
            outputAvailable = false;
        }
    }

    public void write(DataType type, String value) throws IOException {
        if (!outputAvailable) {
            return;
        }

        BufferedWriter writer = writers.get(type);
        if (writer == null) {
            writer = createWriter(type);
            writers.put(type, writer);
        }

        writer.write(value);
        writer.newLine();
    }

    private BufferedWriter createWriter(DataType type) throws IOException {
        String fileName = config.prefix + type.name().toLowerCase() + "s.txt";
        Path filePath = Path.of(config.outputPath, fileName);

        return Files.newBufferedWriter(
                filePath,
                StandardOpenOption.CREATE,
                config.appendMode
                        ? StandardOpenOption.APPEND
                        : StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    public void closeAll() {
        writers.values().forEach(writer -> {
            try {
                writer.close();
            } catch (IOException ignored) {
            }
        });
    }

}
