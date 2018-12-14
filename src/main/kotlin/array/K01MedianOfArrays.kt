package array

object K01MedianOfArrays {

    /**
     * Find the median of two sorted arrays.
     *
     * Solution:
     *
     * 1. Brute force approach: combine into new sorted array, then take the median. Requires at least O(n) time due
     *    merging arrays. Finding median is O(1).
     *
     * 2. Divide and conquer approach:
     *  (a) Determine medians of two arrays
     *  (b) Break down into sub-arrays (including overlapping pivot index if even)
     *      (i) If median is lower, then take upper sub-array
     *      (i) If median is higher, then take lower sub-array
     *  (c) When sub-arrays are both small (<3 elements), then merge and take median.
     */

    @JvmStatic
    fun main(args: Array<String>) {

        val list1 = listOf(1, 2, 3, 4, 5, 6)
        val list2 = listOf(0, 0, 0, 0, 10, 10)

        println("Brute force: " + mergeAndGetMedian(list1, list2))
        println("Divide and conquer: " + compute(list1, list2))
    }

    private fun compute(a: List<Int>, b: List<Int>): Double {
        var _a = a
        var _b = b
        while (_a.size > 2 && _b.size > 2) {
            val median1 = getMedian(_a)
            val median2 = getMedian(_b)
            val isNegative = median1 - median2 < 0
            _a = getSubArray(_a, !isNegative)
            _b = getSubArray(_b, isNegative)
        }
        return mergeAndGetMedian(_a, _b)
    }

    private fun getSubArray(list: List<Int>, low: Boolean): List<Int> {
        val pivot = list.size / 2
        val isEven = list.size % 2 == 0
        val lo = when {
            low -> 0
            isEven -> pivot - 1
            else -> pivot
        }
        val hi = if (low) pivot + 1 else list.size
        return list.subList(lo, hi)
    }

    private fun mergeAndGetMedian(a: List<Int>, b: List<Int>): Double {
        return getMedian((a + b).sorted())
    }

    private fun getMedian(array: List<Int>): Double {
        with (array) {
            return when {
                size == 0 -> 0.0
                size == 1 -> array[0] * 1.0
                size % 2 == 0 -> (array[size/2 - 1] + array[size/2]) / 2.0
                else -> array[size/2] * 1.0
            }
        }
    }





}