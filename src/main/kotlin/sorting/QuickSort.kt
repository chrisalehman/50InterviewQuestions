package sorting


object QuickSort {

    /**
     * Quick sort algorithm.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("quickSort(): expected=[1, 2, 3, 4, 5, 6, 7, 8, 9, 10), actual=" +
                        quickSort(mutableListOf(5,3,8,2,1,10,7,6,9,4), 0, 9))
    }

    private fun quickSort(input: MutableList<Int>, lo: Int, hi: Int): List<Int> {
        if (lo < hi) {
            val pivot = partition(input, lo, hi)
            quickSort(input, lo, pivot-1)
            quickSort(input, pivot+1, hi)
        }
        return input
    }

    private fun partition(input: MutableList<Int>, lo: Int, hi: Int): Int {

        val pivot = input[hi]
        var i = lo - 1

        for (j in lo..(hi-1)) {
            if (input[j] <= pivot) {
                i++
                swap(input, i, j)
            }
        }
        swap(input, i+1, hi)

        return i + 1
    }

    private fun swap(input: MutableList<Int>, a: Int, b: Int) {
        val tmp = input[a]
        input[a] = input[b]
        input[b] = tmp
    }
}