package com.dariaert.cli;

import com.dariaert.config.Config;

public class Parser {

    public Config parse(String[] args){
        Config config = new Config();
        for (int i = 0; i < args.length; i++){
            switch (args[i]){
                case "-a" -> config.appendMode = true;
                case "-s" -> config.shortStats = true;
                case "-f" -> config.fullStats = true;
                case "-o" -> config.outputPath = args[++i];
                case "-p" -> config.prefix = args[++i];
                default -> config.inputFiles.add(args[i]);
            }
        }
        return config;
    }

}
