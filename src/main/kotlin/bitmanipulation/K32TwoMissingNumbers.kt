package bitmanipulation


object K32TwoMissingNumbers {

    /**
     * Given an array containing all the numbers from 1 to n except two, find the two missing numbers.
     *
     * Eg.
     *      missing([4, 2, 3]) = 1, 5
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("missing([4, 2, 3]): expected = (1, 5), actual=" + missing(listOf(4,2,3)))
    }

    private fun missing(a: List<Int>): Pair<Int,Int> {
        return Pair(0,0) // TODO
    }
}