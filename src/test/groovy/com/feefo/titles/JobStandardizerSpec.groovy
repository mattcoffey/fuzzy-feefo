package com.feefo.titles

import spock.lang.Specification

class JobStandardizerSpec extends Specification {
    def "should give best standard job title match for given job title"() {
        expect:
            new JobStandardizer().standardize(title) == expected

        where:
            title                   | expected
            "Java engineer"         | "Software engineer"
            "C# engineer"           | "Software engineer"
            "Engineering developer" | "Software engineer"
            "Accountant"            | "Accountant"
            "Chief Accountant"      | "Accountant"
            "No similarity"         | "Accountant"
    }

    def "should throw when null parameter"() {
        when:
            new JobStandardizer().standardize(null)
        then:
            thrown(IllegalArgumentException)
    }
}
