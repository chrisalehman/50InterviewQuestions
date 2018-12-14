package recursion

import java.util.*


object K23PrintReversedLinkedList {

    /**
     * Given a linked list, write a function that prints the nodes of the list in reverse order.
     *
     * Eg.
     *      printReversedList([1,2,3]) => 3,2,1
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val ll = LinkedList<Int>()
        ll.add(1)
        ll.add(2)
        ll.add(3)

        println("printReversedList(): $ll")
        printReversedList(ll)
    }

    /**
     * Basic recursive logic.
     *      1. Time complexity: O(n)
     *      2. Space complexity: O(1)
     */
    private fun printReversedList(list: LinkedList<Int>) {
        val iter = list.iterator()
        printReversedList(iter.next(), iter)
    }

    private fun printReversedList(item: Int, iterator: Iterator<Int>) {
        if (!iterator.hasNext()) {
            println(item)
            return
        }
        printReversedList(iterator.next(), iterator)
        println(item)
    }
}

