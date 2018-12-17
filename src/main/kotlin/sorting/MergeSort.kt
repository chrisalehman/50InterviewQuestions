package sorting


object MergeSort {

    /**
     * Implement merge sort.
     *
     * Eg.
     *      [5,3,8,2,1,10,7,6,9,4] => [1,2,3,4,5,6,7,8,9,10]
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("mergeSort(): expected=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], actual=" + mergeSort(listOf(5,3,8,2,1,10,7,6,9,4)))
    }

    /**
     * Recursive version of merge sort.
     *
     *      1. Time complexity: O(n log(n))
     *      2. Space complexity: O(n)
     */
    private fun mergeSort(a: List<Int>): List<Int> {
        if (a.size < 2)
            return a

        // split into two halves
        val pivot = a.size / 2
        val left = a.subList(0, pivot)
        val right = a.subList(pivot, a.size)

        // sort halves recursively
        val lSorted = mergeSort(left)
        val rSorted = mergeSort(right)

        // prepare to merge results
        val lIter = lSorted.iterator()
        val rIter = rSorted.iterator()
        var lVal: Int? = if (lIter.hasNext()) lIter.next() else null
        var rVal: Int? = if (rIter.hasNext()) rIter.next() else null
        val merged = mutableListOf<Int>()

        // merge
        while (lVal != null && rVal != null) {
            val diff = lVal - rVal
            if (diff < 0) {
                merged.add(lVal)
                lVal = if (lIter.hasNext()) lIter.next() else null
            }
            else {
                merged.add(rVal)
                rVal = if (rIter.hasNext()) rIter.next() else null
            }
        }

        // merge remainder
        if (lVal != null) {
            merged.add(lVal)
            while (lIter.hasNext()) merged.add(lIter.next())
        }
        if (rVal != null) {
            merged.add(rVal)
            while (rIter.hasNext()) merged.add(rIter.next())
        }

        return merged
    }
}