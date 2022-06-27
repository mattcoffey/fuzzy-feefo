package com.feefo.titles;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MatchScore implements Comparable<MatchScore> {
    String value;
    BigDecimal score;

    @Override
    public int compareTo(MatchScore o) {
        return score.compareTo(o.score);
    }
}
