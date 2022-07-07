package com.feefo.launcher;

import com.feefo.utils.IntArrayCalculator;
import com.feefo.utils.IntArraySummariser;
import lombok.extern.apachecommons.CommonsLog;
import lombok.val;

import java.util.stream.IntStream;

@CommonsLog
public class Main {

    /**
     * Launcher and main program
     * TODO clarify requirements for usages for this software and output format and make a proper class with tests
     *
     * @param args
     */
    public static void main(String... args) {
        int size = parseArguments(args);
        int[] myBigArray = generateSomeHugeArray(size);
        val stats = new IntArraySummariser(new IntArrayCalculator()).getStats(myBigArray);
        log.info("Stats for generated int array: " + stats.toString());
    }

    /**
     * parse program arguments
     *
     * @param args
     * @return the size
     */
    private static int parseArguments(String[] args) {
        try {
            return Integer.parseInt(args[0]);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to parse arguments. First argument should be a positive integer");
        }
    }

    /**
     * TODO clarify requirements about generating array and make a proper class with tests
     *
     * @param size
     * @return an int[] with size defined by parameter of random numbers between 0-100
     */
    private static int[] generateSomeHugeArray(int size) {
        return IntStream.generate(() -> (int) (Math.random() * 100))
                .limit(size)
                .toArray();
    }
}