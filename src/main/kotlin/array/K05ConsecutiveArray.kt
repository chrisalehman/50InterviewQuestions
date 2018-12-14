package array

object K05ConsecutiveArray {

    /**
     * Given an unsorted array, find the length of the longest sequence of consecutive numbers in the array.
     *
     * Eg.
     *      consecutive([4, 2, 1, 6, 5]) = 3, [4, 5, 6]
     *      consecutive([5, 5, 3, 1]) = 1, [1], [3], or [5]
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("findConsecutiveSeq(): expected=3, actual=" + findConsecutiveSeq(
            mutableListOf(
                4,
                2,
                1,
                6,
                5
            )
        )
        )
        println("findConsecutiveSeqUnsorted(): expected=3, actual=" + findConsecutiveSeqUnsorted(
            listOf(4, 2, 1, 6, 5)
        )
        )
    }

    /**
     * With sorting:
     *      1. O(n log n) time complexity due to sort
     *      2. O(n) space complexity
     */
    private fun findConsecutiveSeq(list: MutableList<Int>): Int {

        var maxCount = 0
        var count = 0

        list.sort()
        for (i in list.indices) {
            when {
                count == 0 || list[i] - list[i - 1] == 1 -> {
                    count++
                }
                else -> {
                    maxCount = count
                    count = 1
                }
            }
        }

        return if (maxCount > count) maxCount else count
    }

    /**
     * Without sorting:
     *      1. O(n) time complexity
     *      2. O(n) space complexity
     */
    private fun findConsecutiveSeqUnsorted(list: List<Int>): Int {

        // add all items to set
        val dups: MutableSet<Int> = mutableSetOf()
        for (i in list)
            dups.add(i)

        // iterate through set
        var maxCount = 0
        for (i in dups) {

            // if not at beginning of sequence, bail
            if (dups.contains(i-1)) continue
            var count = 0
            var j = i

            // count entire sequence
            while (dups.contains(j++)) count++
            maxCount = Math.max(count, maxCount)
        }

        return maxCount
    }
}