package com.feefo.utils;

import com.feefo.datamodel.IntArrayStats;

/**
 * Constructs and IntArrayStats object
 */
public class IntArraySummariser {

    private final IntArrayCalculator calculator;

    public IntArraySummariser(IntArrayCalculator calculator) {
        this.calculator = calculator;
    }

    public IntArrayStats getStats(int... input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Can't get stats of empty array");
        }
        return IntArrayStats.builder()
                .mean(calculator.calculateMean(input))
                .median(calculator.calculateMedian(input))
                .mode(calculator.calculateMode(input))
                .range(calculator.calculateRange(input))
                .build();
    }

}