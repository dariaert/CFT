package com.dariaert.config;

import com.dariaert.stats.StatisticsMode;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public boolean appendMode; // -a
    public StatisticsMode statisticsMode; // -s или -f, нужно проверить
    public String outputPath = "."; // -o
    public String prefix = ""; // -p
    public List<String> inputFiles = new ArrayList<>();

}
