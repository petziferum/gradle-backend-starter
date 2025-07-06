package com.petziferum.gradlebackend.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringTest {

    private final LongestSubstring longestSubstring = new LongestSubstring();

    @Test
    void testEmptyString() {
        assertEquals(0, longestSubstring.lengthOfLongestSubstring(""));
    }

    @Test
    void testSingleCharacter() {
        assertEquals(1, longestSubstring.lengthOfLongestSubstring("a"));
    }

    @Test
    void testAllSameCharacters() {
        assertEquals(1, longestSubstring.lengthOfLongestSubstring("aaaaa"));
    }

    @Test
    void testNoRepeatingCharacters() {
        assertEquals(5, longestSubstring.lengthOfLongestSubstring("abcde"));
    }

    @Test
    void testWithRepeatingCharacters() {
        assertEquals(3, longestSubstring.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, longestSubstring.lengthOfLongestSubstring("bbbbb"));
        assertEquals(3, longestSubstring.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void testRepeatingCharactersAtStart() {
        assertEquals(2, longestSubstring.lengthOfLongestSubstring("abba"));
    }

    @Test
    void testRepeatingCharactersAtEnd() {
        assertEquals(2, longestSubstring.lengthOfLongestSubstring("abbc"));
    }
}
