package com.dariaert.stats;

public class StringStatistics implements Statistics {

    private int count;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = Integer.MIN_VALUE;

    @Override
    public void add(String value) {
        int len = value.length();
        count++;
        minLength = Math.min(minLength, len);
        maxLength = Math.max(maxLength, len);
    }

    @Override
    public void print() {
        System.out.println("Количество строк: " + count);
        System.out.println("Минимальная длина: " + minLength);
        System.out.println("Максимальная длина: " + maxLength);
    }

}
