package com.dariaert.cli;

import com.dariaert.config.Config;
import com.dariaert.stats.StatisticsMode;

public class Parser {

    public Config parse(String[] args){
        Config config = new Config();
        for (int i = 0; i < args.length; i++){
            switch (args[i]){
                case "-a" -> config.appendMode = true;
                case "-s" -> {
                    if (config.statisticsMode != null) {
                        System.err.println("Нельзя использовать -s и -f одновременно");
                        return null;
                    }
                    config.statisticsMode = StatisticsMode.SHORT;
                }
                case "-f" -> {
                    if (config.statisticsMode != null) {
                        System.err.println("Нельзя использовать -s и -f одновременно");
                        return null;
                    }
                    config.statisticsMode = StatisticsMode.FULL;
                }
                case "-o" -> {
                    if (i + 1 >= args.length) {
                        System.err.println("Ошибка: опция -o требует аргумент (путь)");
                        return null;
                    }
                    config.outputPath = args[++i];
                }
                case "-p" -> {
                    if (i + 1 >= args.length) {
                        System.err.println("Ошибка: опция -p требует аргумент (префикс)");
                        return null;
                    }
                    config.prefix = args[++i];
                }
                default -> {
                    if (args[i].startsWith("-")) {
                        System.err.println("Неизвестная опция: " + args[i]);
                        return null;
                    }
                    config.inputFiles.add(args[i]);
                }
            }
        }
        return config;
    }
}
