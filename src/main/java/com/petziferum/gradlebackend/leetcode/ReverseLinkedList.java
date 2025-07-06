package com.petziferum.gradlebackend.leetcode;

public class ReverseLinkedList {
    /**
     * Dreht die einfach verkettete Liste um und gibt den neuen Kopf zurück.
     *
     * @param head Der Kopf der ursprünglichen Liste.
     * @return Der Kopf der umgedrehten Liste.
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            // Merke dir den nächsten Knoten
            ListNode nextTemp = curr.next;
            // Zeige current.next auf prev (Umdrehung)
            curr.next = prev;
            // Schiebe prev und curr einen Schritt weiter
            prev = curr;
            curr = nextTemp;
        }

        // Am Ende ist prev der neue Kopf
        return prev;
    }

    // Hilfsmethode zum Erzeugen einer Liste aus einem Array (für Tests)
    private static ListNode buildList(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    // Hilfsmethode zum Ausgeben einer Liste
    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) System.out.print(" -> ");
            curr = curr.next;
        }
        System.out.println();
    }
}
