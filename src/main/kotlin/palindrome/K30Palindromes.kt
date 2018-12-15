package palindrome

import java.util.*


object K30Palindromes {

    /**
     * Given a linked list, write a function to determine whether the list is a palindrome.
     *
     * Eg.
     *      palindrome(1 ‐> 2 ‐> 3) = false
     *      palindrome(1 ‐> 2 ‐> 1) = true
     */
    @JvmStatic
    fun main(args: Array<String>) {

        fun test1() {
            val n3 = Node(3)
            val n2 = Node(2, n3)
            val n1 = Node(1, n2)
            println("palindrom([1,2,3]): expected=false, actual=" + palindrome(n1))
        }

        fun test2() {
            val n3 = Node(1)
            val n2 = Node(2, n3)
            val n1 = Node(1, n2)
            println("palindrom([1,2,1]): expected=true, actual=" + palindrome(n1))
        }

        fun test3() {
            val n4 = Node(1)
            val n3 = Node(2, n4)
            val n2 = Node(2, n3)
            val n1 = Node(1, n2)
            println("palindrom([1,2,2,1]): expected=true, actual=" + palindrome(n1))
        }

        test1()
        test2()
        test3()
    }

    private data class Node(val value: Int, val next: Node?) {
        constructor(value: Int): this(value, null)
        override fun toString(): String = value.toString()
    }

    /**
     * Solution: iterate through linked list until end, populating a stack. Re-iterate, comparing elements against
     * popped stack elements. If all match, it's a palindrome.
     *
     *      1. Time complexity: O(n)
     *      2. Space complexity: O(n)
     */
    private fun palindrome(n: Node): Boolean {

        val s = Stack<Int>()
        var curr: Node? = n

        while (curr != null) {
            s.push(curr.value)
            curr = curr.next
        }
        curr = n
        while (curr != null) {
            if (s.pop() != curr.value)
                return false
            curr = curr.next
        }

        return true
    }
}