package com.petziferum.gradlebackend.leetcode;

public class LeetcodeFunctions {
    public void reverseString(char[] s) {
        int left = 0; //0
        int len = s.length -1; //4
        while(left < len) {
            char temp = s[left];
            s[left] = s[len];
            s[len] = temp;
            left ++;
            len--;
        }
    }

    public int removeDuplicates(int[] nums) {

        if(nums.length == 0) return 0;
        int uIndex = 1;
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i -1]) {
                nums[uIndex] = nums[i];
                uIndex++;
            }
        }
        return uIndex;
    }

    public int maxProfit(int[] prices) {
        int profit = 0;
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}
