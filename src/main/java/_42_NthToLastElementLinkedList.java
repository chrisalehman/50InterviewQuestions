public class _42_NthToLastElementLinkedList {
    /**
     * Find nth to last element of the linked list.
     *
     * Solution:
     *  - Implement two pointers. Increment first pointer to <n. Then increment both until next element is null (end
     *    of list). This will result in natural offset and answer!
     */
    public static void main(String[] args) {

        Node n8 = new Node(); n8.value = 8;
        Node n7 = new Node(); n7.value = 7; n7.next = n8;
        Node n6 = new Node(); n6.value = 6; n6.next = n7;
        Node n5 = new Node(); n5.value = 5; n5.next = n6;
        Node n4 = new Node(); n4.value = 4; n4.next = n5;
        Node n3 = new Node(); n3.value = 3; n3.next = n4;
        Node n2 = new Node(); n2.value = 2; n2.next = n3;
        Node n1 = new Node(); n1.value = 1; n1.next = n2;
        Node n0 = new Node(); n0.value = 0; n0.next = n1;

        System.out.println("Find nth to last node: " + findNthToLastNode(n0, 3));
    }

    private static class Node {
        Node next;
        int value;
    }

    private static int findNthToLastNode(Node node, int n) {

        Node curr = node;
        Node next = node;

        for (int i=0; i<n; i++) {
            curr = curr.next;
        }

        while (curr.next != null) {
            curr = curr.next;
            next = next.next;
        }

        return next.value;
    }
}
