package com.petziferum.gradlebackend.leetcode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReverseLinkedListTest {

    @Test
    void testReverseEmptyList() {
        assertNull(ReverseLinkedList.reverseList(null));
    }

    @Test
    void testReverseSingleNodeList() {
        ListNode list = buildList(new int[]{1});
        ListNode result = ReverseLinkedList.reverseList(list);
        
        assertEquals(1, result.val);
        assertNull(result.next);
    }

    @Test
    void testReverseMultiNodeList() {
        ListNode list = buildList(new int[]{1, 2, 3, 4, 5});
        ListNode expected = buildList(new int[]{5, 4, 3, 2, 1});
        
        ListNode result = ReverseLinkedList.reverseList(list);
        
        assertTrue(listsAreEqual(expected, result));
    }

    @Test
    void testReverseTwoNodeList() {
        ListNode list = buildList(new int[]{1, 2});
        ListNode expected = buildList(new int[]{2, 1});
        
        ListNode result = ReverseLinkedList.reverseList(list);
        
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