package com.dariaert.stats;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntegerStatistics implements Statistics{

    private int count;
    private BigInteger min;
    private BigInteger max;
    private BigInteger sum = BigInteger.ZERO;

    @Override
    public void add(String value) {
        BigInteger v = new BigInteger(value);

        if (count == 0) {
            min = v;
            max = v;
        } else {
            min = min.min(v);
            max = max.max(v);
        }

        sum = sum.add(v);
        count++;
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

            BigDecimal avg = new BigDecimal(sum)
                    .divide(BigDecimal.valueOf(count), RoundingMode.HALF_UP);
            System.out.println("Среднее: " + avg);
        }
    }
}
