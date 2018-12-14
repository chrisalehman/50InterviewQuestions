package recursion


object K26SmallestChange {

    /**
     * Given an input amount of change x, write a function to determine the minimum number of coins required to
     * make that amount of change. Assume U.S. coins.
     *
     * Eg.
     *      change(1) = 1
     *      change(3) = 3
     *      change(7) = 3
     *      change(32) = 4
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("change(): expected=1, actual=" + change(1, listOf(25,10,5,1)))
        println("change(): expected=3, actual=" + change(3, listOf(25,10,5,1)))
        println("change(): expected=3, actual=" + change(7, listOf(25,10,5,1)))
        println("change(): expected=4, actual=" + change(32, listOf(25,10,5,1)))
    }

    /**
     * Uses recursion to find best change.
     *
     * Note that my solution departs from provided one - I use / and % operators, where given solution just subtracts
     * value of coin. My solution should require fewer stack frames.
     *
     *      1. Time complexity: O(n!), where n = number of coin types
     *      2. Space complexity: O(1)
     */
    private fun change(cents: Int, coins: List<Int>): Int {
        if (cents == 0)
            return 0
        var smallestNum = Int.MAX_VALUE
        for (coin in coins) {
            if (cents / coin > 0) {
                val total = (cents / coin) + change(cents % coin, coins)
                if (total < smallestNum)
                    smallestNum = total
            }
        }
        return smallestNum
    }
}