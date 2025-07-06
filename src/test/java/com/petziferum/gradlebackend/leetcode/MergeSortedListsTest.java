package com.petziferum.gradlebackend.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortedListsTest {

    @Test
    void testMergeEmptyLists() {
        assertNull(MergeSortedLists.mergeTwoLists(null, null));
    }

    @Test
    void testMergeWithOneEmptyList() {
        ListNode list1 = buildList(new int[]{1, 2, 3});
        ListNode list2 = null;
        
        ListNode result1 = MergeSortedLists.mergeTwoLists(list1, list2);
        assertTrue(listsAreEqual(list1, result1));
        
        ListNode result2 = MergeSortedLists.mergeTwoLists(null, list1);
        assertTrue(listsAreEqual(list1, result2));
    }

    @Test
    void testMergeTwoSortedLists() {
        ListNode list1 = buildList(new int[]{1, 2, 4});
        ListNode list2 = buildList(new int[]{1, 3, 4});
        ListNode expected = buildList(new int[]{1, 1, 2, 3, 4, 4});
        
        ListNode result = MergeSortedLists.mergeTwoLists(list1, list2);
        
        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testMergeWithNonoverlappingLists() {
        ListNode list1 = buildList(new int[]{1, 2, 3});
        ListNode list2 = buildList(new int[]{4, 5, 6});
        ListNode expected = buildList(new int[]{1, 2, 3, 4, 5, 6});
        
        ListNode result = MergeSortedLists.mergeTwoLists(list1, list2);
        
        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testMergeWithDifferentLengthLists() {
        ListNode list1 = buildList(new int[]{1, 3, 5, 7, 9});
        ListNode list2 = buildList(new int[]{2, 4, 6});
        ListNode expected = buildList(new int[]{1, 2, 3, 4, 5, 6, 7, 9});
        
        ListNode result = MergeSortedLists.mergeTwoLists(list1, list2);
        
        assertTrue(listsAreEqual(expected, result));
    }

    // Helper method to build a linked list from an array
    private ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Helper method to check if two lists are equal
    private boolean listsAreEqual(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        // Both lists should end at the same time
        return l1 == null && l2 == null;
    }
}