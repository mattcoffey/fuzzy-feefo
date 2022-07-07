package com.feefo.utils

import com.feefo.datamodel.IntArrayStats
import spock.lang.Specification

class IntArrayCalculatorSpec extends Specification {

    def tested = new IntArrayCalculator()

    def "should calculate mode"() {
        expect:
            tested.calculateMode(input) == expected

        where:
            input                                    | expected
            toArray([1, 2, 3])                       | [1, 2, 3].toSet()
            toArray([1, 2, 6])                       | [1, 2, 6].toSet()
            toArray([1, 2, 2, 6])                    | [2].toSet()
            toArray([-1, -1, -1, 0, 0, 0])           | [-1, 0].toSet()
            toArray([10, -1, -1, 4, -1, 0, 0, 3, 0]) | [-1, 0].toSet()
    }


    def "should calculate mean"() {
        expect:
            tested.calculateMean(input) == expected

        where:
            input               | expected
            toArray([3, 2, 1])  | 2
            toArray([0, -1])    | BigDecimal.valueOf(-0.5)
            toArray([-1, 2, 8]) | 3
    }

    def "should calculate median"() {
        expect:
            tested.calculateMedian(input) == expected

        where:
            input                | expected
            toArray([3, 2, 1])   | 2
            toArray([0, -1])     | BigDecimal.valueOf(-0.5)
            toArray([-1, 2, 10]) | 2
    }

    def "should calculate range"() {
        expect:
            tested.calculateRange(input) == expected

        where:
            input                | expected
            toArray([3, 2, 1])   | 2
            toArray([0, -1])     | 1
            toArray([-1, 2, 10]) | 11
    }

    static int[] toArray(List<Integer> integers) {
        return integers.stream().mapToInt(Integer::intValue).toArray();
    }
}