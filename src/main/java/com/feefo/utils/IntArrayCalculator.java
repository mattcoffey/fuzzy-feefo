package com.feefo.utils;

import lombok.val;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * Calculates numeric stats from an int[]
 * TODO protect methods in this class from null or empty arrays and add to tests
 */
public class IntArrayCalculator {

    /**
     * @param input
     * @return the average of the numbers in input
     */
    BigDecimal calculateMean(int[] input) {
        val list = IntStream.of(input);
        return BigDecimal.valueOf(list.average().getAsDouble());
    }

    /**
     * @param input
     * @return the median for the numbers in input
     */
    BigDecimal calculateMedian(int[] input) {
        val list = Arrays.stream(input).sorted().toArray();
        val medianPosition = ((list.length + 1) / 2) - 1;
        if (list.length % 2 == 0) {
            return BigDecimal.valueOf(
                    IntStream.of(list[medianPosition], list[medianPosition + 1])
                            .average().getAsDouble());
        } else {
            return BigDecimal.valueOf(list[medianPosition]);
        }
    }

    /**
     * @param input
     * @return the range of values
     */
    int calculateRange(int[] input) {
        return IntStream.of(input).max().getAsInt()
                - IntStream.of(input).min().getAsInt();
    }

    /**
     * (probably a better way of doing this using stream groupby)
     *
     * @param input
     * @return the set of equally most frequently occurring members of this dataset
     */
    Set<Integer> calculateMode(int[] input) {
        Set<Integer> modes = new HashSet<>();
        var occurrences = 0;
        var previousMostOccurrences = 0;
        for (int distinct : IntStream.of(input).distinct().toArray()) {
            occurrences = frequency(input, distinct);
            if (previousMostOccurrences < occurrences) {
                modes.clear();
                modes.add(distinct);
                previousMostOccurrences = occurrences;
            } else if (previousMostOccurrences == occurrences) {
                modes.add(distinct);
            }
        }
        return modes;
    }

    private int frequency(int[] input, int distinct) {
        int occurrences = 0;
        for (int i : input) {
            if (i == distinct) {
                occurrences++;
            }
        }
        return occurrences;
    }
}