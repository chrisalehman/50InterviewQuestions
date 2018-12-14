package array

object K11ZeroSumSubarray {

    /**
     * Given an array, write a function to find the largest subarray that sums to zero, if one exists.
     *
     * Eg.
     *      zeroSum({1, 2, ‐5, 1, 2, 5}) = [2, ‐5, 1, 2]
     *
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("zeroSum(): expected=[2, -5, 1, 2], actual=${zeroSum(
            listOf(
                1,
                2,
                -5,
                1,
                2,
                5
            )
        )}")
        println("zeroSumDynamic(): expected=[2, -5, 1, 2], actual=${zeroSumDynamic(
            listOf(
                1,
                2,
                -5,
                1,
                2,
                5
            )
        )}")
    }

    /**
     * Brute force:
     *      1. O(n^2) time complexity
     *      2. O(n) space complexity
     *
     * Strategy is to nest two loops and try every combination. Not the best!
     */
    private fun zeroSum(list: List<Int>): List<Int> {

        // handle corner input cases
        if (list.isEmpty())
            return listOf()
        if (list.size == 1 && list[0] == 0)
            return listOf(0)

        var maxList = listOf<Int>()
        val subList = mutableListOf<Int>()

        for (i in list.indices) {

            // start searching at index i
            subList.clear()
            var sum = list[i]
            subList.add(sum)

            // handle corner case of zero single-element sub-list
            if (sum == 0 && maxList.isEmpty()) {
                maxList = subList.toList()
            }

            // bail if at last index
            if (i == list.size - 1) continue

            // look for sub-lists that aggregate to zero
            for (j in (i + 1)..(list.size - 1)) {
                subList.add(list[j])
                sum += list[j]
                if (sum == 0 && subList.size > maxList.size) {
                    maxList = subList.toList()
                }
            }
        }

        return maxList
    }

    /**
     * Dynamic method utilizing progressive sum computed at each index. Any incidence of repetition indicates
     * a solution. Also, a sum of zero anywhere along the path also indicates a solution.
     *
     * Eg.
     *      zeroSum({1, 2, ‐5,  1, 2, 5}) = [2, ‐5, 1, 2]
     *      Sums:    1, 3, -2, -1, 1, 6     => 1 repeats
     *
     *      1. O(n) time complexity - only one iteration
     *      2. O(n) space complexity - storing incidence of sums in map
     */
    private fun zeroSumDynamic(list: List<Int>): List<Int> {

        val sumToIndexes = mutableMapOf<Int, MutableList<Int>>()

        var sum = 0
        var maxLength = 0
        var maxSum = 0

        for (i in list.indices) {

            // populate frequency map of indexes with same sum
            sum += list[i]
            val indexes = sumToIndexes[sum] ?: mutableListOf()
            if (indexes.isEmpty())
                sumToIndexes[sum] = indexes
            indexes.add(i)

            // if sum is zero, then record this - it indicates solution starting from beginning
            if (sum == 0 && i > maxLength) {
                maxLength = i
                maxSum = sum
            }

            // if multiple indexes found for same sum, indicates a solution from start+1 (inclusive) to
            // end (exclusive)
            else if (indexes.size > 1) {
                val length = indexes[indexes.size - 1] - indexes[0]
                if (length > maxLength) {
                    maxLength = length
                    maxSum = sum
                }
            }
        }

        val result = sumToIndexes[maxSum] ?: mutableListOf()

        return when {
            result.isEmpty() -> result
            result.size == 1 -> {
                if (result[0] == 0) buildSubList(list, 0, result[0])
                else listOf()
            }
            else -> buildSubList(list, result[0] + 1, result[result.size - 1] + 1)
        }
    }

    private fun buildSubList(list: List<Int>, startIndex: Int, endIndex: Int): List<Int> {
        val result = mutableListOf<Int>()
        for (i in startIndex..(endIndex-1)) {
            result.add(list[i])
        }
        return result
    }
}