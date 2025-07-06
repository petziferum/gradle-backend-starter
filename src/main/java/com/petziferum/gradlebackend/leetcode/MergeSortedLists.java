package com.petziferum.gradlebackend.leetcode;

public class MergeSortedLists {
    /**
     * Iterative Lösung: Splice die beiden Listen zusammen.
     *
     * @param l1 Kopf der ersten sortierten Liste
     * @param l2 Kopf der zweiten sortierten Liste
     * @return Kopf der zusammengeführten sortierten Liste
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // Dummy-Head erleichtert das Handling des neuen Kopfes
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        // Solange beide Listen noch Elemente haben...
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        // Hänge die verbleibenden Knoten an (eine der Listen ist jetzt leer)
        tail.next = (l1 != null) ? l1 : l2;

        // dummy.next ist der echte Kopf der neuen Liste
        return dummy.next;
    }

    private static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0), tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }
}
