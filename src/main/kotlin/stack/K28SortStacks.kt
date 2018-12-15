package stack

import java.util.*


object K28SortStacks {

    /**
     * Given a stack, sort the elements in the stack using one additional stack.
     *
     * Eg.
     *      sort([1, 3, 2, 4]) = [1, 2, 3, 4]
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val s = Stack<Int>()
        s.push(4)
        s.push(2)
        s.push(3)
        s.push(1)
        s.push(7)
        s.push(5)
        s.push(0)
        s.push(6)
        s.push(10)
        s.push(9)
        s.push(8)
        println("sort(): expected=<asc order>, actual=" + sort(s))
    }

    /**
     *  Use a temp variable. Key is that if tmp > what's in destination, move items over to src and then push
     *  tmp to dest.
     *
     *      1. Time complexity: O(n!)
     *      2. Space complexity: O(1)
     */
    private fun sort(src: Stack<Int>): List<Int> {

        val dst = Stack<Int>()

        while (!src.isEmpty()) {
            val tmp = src.pop()
            while (!dst.isEmpty() && tmp > dst.peek())
                src.push(dst.pop())
            dst.push(tmp)
        }

        // convert into list for printing reasons
        val list = mutableListOf<Int>()
        while (!dst.isEmpty())
            list.add(dst.pop())

        return list
    }
}