package string


object K49Fibonacci {

    /**
     * Given an integer n, write a function to compute the nth Fibonacci number.
     *
     *      nth  = 0, 1, 2, 3, 4, 5, 6, 7,  8,  9
     * Eg. fib() = 0, 1, 1, 2, 3, 5, 8, 13, 21, 34
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("fibonacci1(6): expected=8, actual=" + fibonacci1(6))
        println("fibonacci2(6): expected=8, actual=" + fibonacci2(6))
        println("fibonacci1(10): expected=55, actual=" + fibonacci1(10))
        println("fibonacci2(10): expected=55, actual=" + fibonacci2(10))
    }

    /**
     * Classic fibonacci implemented with recursion. Not efficient, solving it in exponential time.
     *
     *      1. Time complexity: O(2^n)
     *      2. Space complexity: O(1)
     */
    private fun fibonacci1(n: Int): Int {
        if (n < 2)
            return n
        return fibonacci1(n-2) + fibonacci1(n-1)
    }

    /**
     * Classic fibonacci implemented with a while loop. Preferable to recursion.
     *
     *      1. Time complexity: O(n)
     *      2. Space complexity: O(1)
     */
    private fun fibonacci2(n: Int): Int {
        if (n < 0)
            return -1
        if (n < 2)
            return n

        var prev = 0
        var curr = 1
        var count = 2

        while (count <= n) {
            val tmp = curr
            curr += prev
            prev = tmp
            count += 1
        }
        return curr
    }
}