package com.dariaert.stats;

public interface Statistics {
    void add(String value);
    void print(StatisticsMode mode);
    boolean hasData();
}
