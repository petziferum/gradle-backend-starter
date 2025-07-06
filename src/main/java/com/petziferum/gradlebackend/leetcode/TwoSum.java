package com.petziferum.gradlebackend.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * Findet zwei Indizes i und j mit nums[i] + nums[j] == target.
     * @param nums   Eingabe-Array
     * @param target Ziel-Summe
     * @return       Array mit den beiden Indizes [i, j]
     */
    public static int[] twoSum(int[] nums, int target) {
        // Map von Wert → Index
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            // Prüfen, ob wir das Gegenstück schon gesehen haben
            if (map.containsKey(complement)) {
                // ja: wir haben Lösung gefunden
                return new int[] { map.get(complement), i };
            }
            // nein: aktuellen Wert merken
            map.put(nums[i], i);
        }

        // Laut Problemstellung gibt es immer genau eine Lösung
        throw new IllegalArgumentException("No two sum solution");
    }
}
