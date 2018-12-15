package stack

import java.util.Queue
import java.util.LinkedList


object K30StacksFromQueues {

    /**
     * Implement a LIFO stack with basic functionality (push and pop) using FIFO queues to store the data.
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val s = Stack<Int>()
        s.push(5)
        s.push(4)
        s.push(3)
        s.push(2)
        s.push(1)

        val list = mutableListOf<Int>()
        while (!s.isEmpty())
            list.add(s.pop())

        println("5,4,3,2,1 => $list")
    }

    /**
     * Implemented with two arbitrary queues.
     *      1. push():  push items onto non-empty queue
     *      2. pop():   transfer all items from non-empty q to empty q, except return last element
     *
     * Complexity:
     *      1. Time:
     *          a) push() - O(1)
     *          b) pop() - O(n)
     *      2. Space: O(n)
     *
     * NOTE: Can implement it in the opposite direction, i.e., O(1) time for pop():
     *      a) Must all items over except last one - the 1-item queue represents last item
     *      b) Push() requires transfer of last item over to other queue, enqueue, then transfer all but last back
     */
    private class Stack<E> {

        private val a: Queue<E> = LinkedList()
        private val b: Queue<E> = LinkedList()

        fun isEmpty(): Boolean = a.size + b.size == 0

        fun push(element: E): E {
            val q = if (a.size - b.size > 0) a else b
            q.add(element)
            return element
        }

        fun pop(): E {
            val src = if (a.size - b.size > 0) a else b
            val dst = if (a.size - b.size > 0) b else a
            while (src.size > 1)
                dst.add(src.poll())
            return src.poll()
        }
    }
}