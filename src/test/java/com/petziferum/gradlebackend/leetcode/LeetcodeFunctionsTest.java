package com.petziferum.gradlebackend.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LeetcodeFunctionsTest {

    private final LeetcodeFunctions leetcodeFunctions = new LeetcodeFunctions();

    @Test
    void testReversePetziferum() {
        char[] input = {'P','e','t','z','i','f','e','r','u','m'};
        leetcodeFunctions.reverseString(input);
        assertArrayEquals(new char[]{'m','u', 'r','e','f','i','z','t','e','P'}, input);
    }

    @Test
    void testReverseEmptyString() {
        char[] input = {};
        leetcodeFunctions.reverseString(input);
        assertArrayEquals(new char[]{}, input);
    }

    @Test
    void testReverseSingleCharString() {
        char[] input = {'a'};
        leetcodeFunctions.reverseString(input);
        assertArrayEquals(new char[]{'a'}, input);
    }

    @Test
    void testReverseEvenLengthString() {
        char[] input = {'H', 'e', 'l', 'l','o'};
        leetcodeFunctions.reverseString(input);
        assertArrayEquals(new char[]{'o', 'l', 'l', 'e', 'H'}, input);
    }

    @Test
    void testReverseOddLengthString() {
        char[] input = {'h', 'e', 'l', 'l', 'o'};
        leetcodeFunctions.reverseString(input);
        assertArrayEquals(new char[]{'o', 'l', 'l', 'e', 'h'}, input);
    }

    // Tests for removeDuplicates method
    @Test
    void testRemoveDuplicatesEmptyArray() {
        int[] nums = {};
        int length = leetcodeFunctions.removeDuplicates(nums);
        assertEquals(0, length);
    }

    @Test
    void testRemoveDuplicatesSingleElement() {
        int[] nums = {1};
        int length = leetcodeFunctions.removeDuplicates(nums);
        assertEquals(1, length);
        assertArrayEquals(new int[]{1}, arraySlice(nums, length));
    }

    @Test
    void testRemoveDuplicatesNoDuplicates() {
        int[] nums = {1, 2, 3, 4, 5};
        int length = leetcodeFunctions.removeDuplicates(nums);
        assertEquals(5, length);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arraySlice(nums, length));
    }

    @Test
    void testRemoveDuplicatesWithDuplicates() {
        int[] nums = {1, 1, 2, 2, 3, 4, 4, 5};
        int length = leetcodeFunctions.removeDuplicates(nums);
        assertEquals(5, length);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arraySlice(nums, length));
    }

    @Test
    void testRemoveDuplicatesAllDuplicates() {
        int[] nums = {1, 1, 1, 1, 1};
        int length = leetcodeFunctions.removeDuplicates(nums);
        assertEquals(1, length);
        assertArrayEquals(new int[]{1}, arraySlice(nums, length));
    }

    // Tests for maxProfit method
    @Test
    void testMaxProfitEmptyArray() {
        int[] prices = {};
        int profit = leetcodeFunctions.maxProfit(prices);
        assertEquals(0, profit);
    }

    @Test
    void testMaxProfitSingleDay() {
        int[] prices = {7};
        int profit = leetcodeFunctions.maxProfit(prices);
        assertEquals(0, profit);
    }

    @Test
    void testMaxProfitDecreasingPrices() {
        int[] prices = {7, 6, 5, 4, 3};
        int profit = leetcodeFunctions.maxProfit(prices);
        assertEquals(0, profit);
    }

    @Test
    void testMaxProfitIncreasingPrices() {
        int[] prices = {1, 2, 3, 4, 5};
        int profit = leetcodeFunctions.maxProfit(prices);
        assertEquals(4, profit);
    }

    @Test
    void testMaxProfitFluctuatingPrices() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int profit = leetcodeFunctions.maxProfit(prices);
        assertEquals(7, profit); // Buy at 1, sell at 5, buy at 3, sell at 6
    }

    // Helper method to get a slice of an array
    private int[] arraySlice(int[] array, int length) {
        int[] result = new int[length];
        System.arraycopy(array, 0, result, 0, length);
        return result;
    }
}