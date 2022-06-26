package com.feefo.datamodel;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Builder
@ToString
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class IntArrayStats {
    private BigDecimal median;
    private BigDecimal mean;
    private Set<Integer> mode;
    private int range;
}
