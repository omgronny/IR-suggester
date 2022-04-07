package similarity;

import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity {

    public static double distance(CharSequence left, CharSequence right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return 1 - Math.round(calculateJaccardSimilarity(left, right) * 100d) / 100d;
    }

    private static double calculateJaccardSimilarity(CharSequence left, CharSequence right) {

        Set<String> intersectionSet = new HashSet<>();
        Set<String> unionSet = new HashSet<>();

        boolean unionFilled = false;
        int leftLength = left.length();
        int rightLength = right.length();

        if (leftLength == 0 || rightLength == 0) {
            return 0d;
        }

        for (int leftIndex = 0; leftIndex < leftLength; leftIndex++) {
            unionSet.add(String.valueOf(left.charAt(leftIndex)));
            for (int rightIndex = 0; rightIndex < rightLength; rightIndex++) {

                if (!unionFilled) {
                    unionSet.add(String.valueOf(right.charAt(rightIndex)));
                }

                if (left.charAt(leftIndex) == right.charAt(rightIndex)) {
                    intersectionSet.add(String.valueOf(left.charAt(leftIndex)));
                }

            }
            unionFilled = true;
        }
        return (double) intersectionSet.size() / (double) unionSet.size();
    }
}