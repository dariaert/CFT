package com.dariaert.config;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public boolean appendMode; // -a
    public boolean shortStats; // -s
    public boolean fullStats; // -f
    public String outputPath = "."; // -o
    public String prefix = ""; // -p
    public List<String> inputFiles = new ArrayList<>();

}
