package similarity;

import requests.Request;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

public class Dictionary {

    private static Set<String> dictionary = new HashSet<>();
    static JaroWinkler jaroWinkler = new JaroWinkler();

    public static void insert(String request) {
        dictionary.add(request);
    }

    public static Set<String> getDictionary() {
        return dictionary;
    }

    public static String replaceWord(String word) {

        String result = word;
        double minDist = 0.5;

        for (String s : dictionary) {

            double currentDist = jaroWinkler.distance(s, word);

            if (currentDist <= minDist) {

                minDist = currentDist;
                result = s;

                if (minDist <= 0.1) {
                    return result;
                }

            }
        }

        return result;

    }

}
