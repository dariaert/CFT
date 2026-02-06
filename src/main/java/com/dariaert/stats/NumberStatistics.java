package com.dariaert.stats;

public class NumberStatistics implements Statistics{

    private int count;
    private double min = Double.MAX_VALUE;
    private double max = Double.MIN_VALUE;
    private double sum;

    @Override
    public void add(String value) {
        double v = Double.parseDouble(value);
        count++;
        min = Math.min(min, v);
        max = Math.max(max, v);
        sum += v;
    }

    @Override
    public void print() {
        System.out.println("Количество: " + count);
        System.out.println("Минимум: " + min);
        System.out.println("Максимум: " + max);
        System.out.println("Сумма: " + sum);
        System.out.println("Среднее: " + sum / count);
    }

}
