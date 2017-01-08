package de.akquinet.jpapitfalls.experiment.util;

import java.util.Collection;

/**
 * Created by Michael Bouschen on 28.12.16.
 */
public class Utilities {

    private Utilities() {}

    public static <E> void printCollection(Collection<E> result, StringBuilder sb) {
        if (result != null) {
            for (E elem : result) {
                sb.append(elem);
                sb.append("<br>");
            }
        }
    }

    public static int compareStrings(String s1, String s2) {
        if (s1 == null) {
            return s2 == null ? 0 : 1;
        } else if (s2 == null) {
            return -1;
        } else {
            return s1.compareTo(s2);
        }
    }
}
