package com.petziferum.gradlebackend.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstring {
    /**
     * Gibt die Länge des längsten Teilstrings ohne wiederholte Zeichen zurück.
     * @param s Eingabestring
     * @return  Länge des längsten Substrings ohne Duplikate
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastIndex = new HashMap<>();
        int maxLen = 0;
        int windowStart = 0;  // linker Zeiger des Sliding Window

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char c = s.charAt(windowEnd);

            // Wenn Zeichen c schon im aktuellen Window ist, verschiebe windowStart
            if (lastIndex.containsKey(c)) {
                // Wichtig: windowStart darf nur vorwärts bewegt werden
                windowStart = Math.max(windowStart, lastIndex.get(c) + 1);
            }

            // Aktualisiere zuletzt gesehenen Index von c
            lastIndex.put(c, windowEnd);

            // Fenstergröße = windowEnd - windowStart + 1
            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }

        return maxLen;
    }
}
