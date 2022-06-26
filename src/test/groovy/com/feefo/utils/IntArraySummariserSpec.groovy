package com.feefo.utils


import spock.lang.Specification

class IntArraySummariserSpec extends Specification {

    def MEDIAN = BigDecimal.valueOf(1.0)
    def MEAN = BigDecimal.valueOf(2.0)
    def MODE = [1,2,3].toSet()
    def RANGE = 4

    IntArrayCalculator calculator
    IntArraySummariser tested

    def setup() {
        calculator = Mock()
        tested = new IntArraySummariser(calculator)
    }

    def "should construct IntArrayStats"() {
        given:
            int[] stub = [1, 2, 3] // Array values are not used. Using Stub() does not work because of bug
        when:
            def actual = tested.getStats(stub)
        then:
            1 * calculator.calculateMedian(stub) >> MEDIAN
            1 * calculator.calculateMean(stub) >> MEAN
            1 * calculator.calculateMode(stub) >> MODE
            1 * calculator.calculateRange(stub) >> RANGE
    }

    def "should throw an exception for empty arrays"() {
        when:
            tested.getStats(new int[0])
        then:
            thrown(IllegalArgumentException.class)
    }

    def "should throw an exception for null parameter"() {
        when:
            tested.getStats(null)
        then:
            thrown(IllegalArgumentException.class)
    }
}