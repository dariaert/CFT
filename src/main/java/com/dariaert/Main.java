package com.dariaert;

import com.dariaert.classifier.DataClassifier;
import com.dariaert.classifier.DataType;
import com.dariaert.cli.Parser;
import com.dariaert.config.Config;
import com.dariaert.reader.FileReader;
import com.dariaert.stats.FloatStatistics;
import com.dariaert.stats.IntegerStatistics;
import com.dariaert.stats.Statistics;
import com.dariaert.stats.StringStatistics;
import com.dariaert.writer.FileWriter;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Config config = parser.parse(args);

        if (config == null) {
            return;
        }

        if (config.inputFiles.isEmpty()) {
            System.err.println("Не указаны входные файлы");
            return;
        }

        DataClassifier classifier = new DataClassifier();
        FileReader reader = new FileReader();
        FileWriter writer = new FileWriter(config);

        Map<DataType, Statistics> stats = Map.of(
                DataType.INTEGER, new IntegerStatistics(),
                DataType.FLOAT, new FloatStatistics(),
                DataType.STRING, new StringStatistics()
        );

        for (String fileName : config.inputFiles) {
            List<String> lines = reader.readLines(fileName);

            if (lines.isEmpty()) { // проверка строки длины 0
                continue;
            }

            for (String line : lines) {
                if (line.isBlank()) { // проверка строки из пробелов
                    continue;
                }
                try {
                    DataType type = classifier.classify(line);
                    writer.write(type, line);
                    stats.get(type).add(line);

                } catch (Exception e) {
                    System.err.println(
                            "Ошибка обработки строки \"" + line + "\" из файла " + fileName
                    );
                }
            }
        }

        writer.closeAll();

        if (config.statisticsMode != null) {
            stats.forEach((type, statistics) -> {
                if (statistics.hasData()) {
                    System.out.println("\nСтатистика для " + type);
                    statistics.print(config.statisticsMode);
                }
            });
        }
    }
}