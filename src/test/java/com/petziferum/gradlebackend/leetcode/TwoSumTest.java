package com.petziferum.gradlebackend.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TwoSumTest {

    @Test
    void testBasicCase() {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] expected = {0, 1};

        assertArrayEquals(expected, TwoSum.twoSum(nums, target));
    }

    @Test
    void testWithNegativeNumbers() {
        int[] nums = {-3, 4, 3, 90};
        int target = 0;
        int[] expected = {0, 2};

        assertArrayEquals(expected, TwoSum.twoSum(nums, target));
    }

    @Test
    void testWithDuplicateNumbers() {
        int[] nums = {3, 3};
        int target = 6;
        int[] expected = {0, 1};

        assertArrayEquals(expected, TwoSum.twoSum(nums, target));
    }

    @Test
    void testWithLargerArray() {
        int[] nums = {1, 5, 8, 3, 9, 2, 7, 4};
        int target = 7;
        int[] expected = {1, 5}; // 5 + 2 = 7

        assertArrayEquals(expected, TwoSum.twoSum(nums, target));
    }

    @Test
    void testNoSolution() {
        int[] nums = {1, 2, 3, 4};
        int target = 10;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TwoSum.twoSum(nums, target);
        });

        assertEquals("No two sum solution", exception.getMessage());
    }
}
