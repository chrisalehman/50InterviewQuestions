package recursion

import java.util.*


object K20ReverseStack {

    /**
     * Given a stack, reverse the items without creating any additional data structures.
     *
     * Eg.
     *      reverse(1‐>2‐>3) = 3‐>2‐>1
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val stack = Stack<Int>()
        stack.push(3)
        stack.push(2)
        stack.push(1)
        println("reverse(): " + reverse(null, stack))
    }

    private fun reverse(item: Int?, stack: Stack<Int>): Stack<Int> {
        if (stack.isEmpty()) {
            stack.push(item)
            return stack
        }
        val next = stack.pop()
        val reversed = reverse(next, stack)
        if (item != null) reversed.push(item)
        return reversed
    }
}