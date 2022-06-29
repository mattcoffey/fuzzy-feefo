package com.feefo.titles;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.Set;

public class JobStandardizer {

    private static final Set<String> standardTitles =
            Set.of("Architect", "Software engineer", "Quantity surveyor", "Accountant");

    /**
     * @param title a non standardized job title
     * @return the probable best match standard title for the parameter
     */
    public String standardize(String title) {
        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        return standardTitles.stream()
                .map((standard) -> MatchScore.builder().value(standard).score(score(title, standard)).build())
                .max(Comparator.naturalOrder()).get().getValue();
    }

    /**
     * TODO consider splitting scoring logic out into a separate class so that it can be reused for different things
     *
     * @param a
     * @param b
     * @return the sum of the match scores for each word divided by the number of words
     */
    private BigDecimal score(String a, String b) {
        if (a.equals(b)) {
            return BigDecimal.valueOf(1.0);
        }
        BigDecimal score = BigDecimal.valueOf(0.0);
        String[] aWords = a.toLowerCase().split(" ");
        String[] bWords = b.toLowerCase().split(" ");
        for (String bWord : bWords) {
            for (String aWord : aWords) {
                if (bWord.equals(aWord)) {
                    score = score.add(BigDecimal.valueOf(1.0));
                } else if (bWord.contains(aWord) || aWord.contains(bWord)) {
                    score = score.add(BigDecimal.valueOf(0.8));
                } else {
                    score = score.add(lettersScore(aWord, bWord));
                }
            }
        }

        return score.divide(BigDecimal.valueOf(aWords.length), new MathContext(3));
    }

    /**
     * @param a
     * @param b
     * @return the number of letters that are contained in the other word (both ways) divided by the total number of
     * letters in both words
     * multiplied by 0.5 (lower weight on this relatively inaccurate factor)
     */
    private BigDecimal lettersScore(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (b.indexOf(a.charAt(i)) != -1) {
                count++;
            }
        }

        for (int i = 0; i < b.length(); i++) {
            if (a.indexOf(b.charAt(i)) != -1) {
                count++;
            }
        }

        return BigDecimal.valueOf(count)
                .divide(BigDecimal.valueOf(b.length() + a.length()), new MathContext(4))
                .multiply(BigDecimal.valueOf(0.5));
    }
}