package com.dariaert;

import com.dariaert.classifier.DataClassifier;
import com.dariaert.classifier.DataType;
import com.dariaert.cli.Parser;
import com.dariaert.config.Config;
import com.dariaert.reader.FileReader;
import com.dariaert.stats.NumberStatistics;
import com.dariaert.stats.Statistics;
import com.dariaert.stats.StringStatistics;
import com.dariaert.writer.FileWriter;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        Config config = parser.parse(args);

        DataClassifier classifier = new DataClassifier();
        FileReader reader = new FileReader();
        FileWriter writer = new FileWriter(config);

        Map<DataType, Statistics> stats = Map.of(
                DataType.INTEGER, new NumberStatistics(),
                DataType.FLOAT, new NumberStatistics(),
                DataType.STRING, new StringStatistics()
        );

        for (String file : config.inputFiles) {
            for (String line : reader.readLines(file)) {
                try {
                    DataType type = classifier.classify(line);
                    writer.write(type, line);
                    stats.get(type).add(line);
                } catch (Exception e) {
                    System.err.println("Ошибка обработки строки: " + line);
                }
            }
        }

        writer.closeAll();

        stats.forEach((type, stat) -> {
            System.out.println("\nСтатистика для " + type);
            stat.print();
        });
    }
}