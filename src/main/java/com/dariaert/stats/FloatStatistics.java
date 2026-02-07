package com.dariaert.stats;

public class FloatStatistics implements Statistics{

    private int count;
    private double min = Double.MAX_VALUE;
    private double max = -Double.MAX_VALUE;
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
    public boolean hasData() {
        return count > 0;
    }

    @Override
    public void print(StatisticsMode mode) {
        System.out.println("Количество: " + count);

        if (mode == StatisticsMode.FULL) {
            System.out.println("Минимум: " + min);
            System.out.println("Максимум: " + max);
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее: " + (sum / count));
        }
    }

}
