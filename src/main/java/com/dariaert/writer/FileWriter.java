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

    public FileWriter(Config config) {
        this.config = config;
    }

    public void write(DataType type, String value) throws IOException {
        BufferedWriter writer = writers.get(type);
        if (writer == null) {
            writer = createWriter(type);
            writers.put(type, writer);
        }
        writer.write(value);
        writer.newLine();
    }

    private BufferedWriter createWriter(DataType type) throws IOException {
        String fileName = config.outputPath + "/" +
                config.prefix + type.name().toLowerCase() + "s.txt";

        return Files.newBufferedWriter(
                Path.of(fileName),
                StandardOpenOption.CREATE,
                config.appendMode ? StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    public void closeAll() {
        writers.values().forEach(w -> {
            try {
                w.close();
            } catch (IOException ignored) {}
        });
    }

}
