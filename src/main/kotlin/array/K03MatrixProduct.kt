package array

object K03MatrixProduct {

    /**
     * Given a matrix, find the path from top left to bottom right with the greatest product by moving only
     * down and right.
     *
     * Eg.
     *      [1, 2, 3]
     *      [4, 5, 6]
     *      [7, 8, 9]
     *                  1 ‐> 4 ‐> 7 ‐> 8 ‐> 9
     *                  2016
     *      [‐1, 2, 3]
     *      [4, 5, ‐6]
     *      [7, 8, 9]
     *                  ‐1 ‐> 4 ‐> 5 ‐> ‐6 ‐> 9
     *                  1080
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val matrix1 = listOf(
            listOf(1,2,3),
            listOf(4,5,6),
            listOf(7,8,9))
        val matrix2 = listOf(
            listOf(-1,2,3),
            listOf(4,5,-6),
            listOf(7,8,9))

        println("findProductRecurse(): expected=2016, actual=" + findProductRecurse(
            matrix1,
            0,
            0,
            1
        )
        )
        println("findProductRecurse(): expected=1080, actual=" + findProductRecurse(
            matrix2,
            0,
            0,
            1
        )
        )
        println("findProductDynamic(): expected=2016, actual=" + findProductDynamic(matrix1))
        println("findProductDynamic(): expected=1080, actual=" + findProductDynamic(matrix2))
    }

    /**
     * Simple recursive solution => O(n!), where n = matrix dimension length
     */
    private fun findProductRecurse(matrix: List<List<Int>>, i: Int, j: Int, accum: Int): Int {

        val acc: Int = accum * matrix[i][j]

        return if (i == matrix.size - 1 && j == matrix.size - 1) acc
        else {
            val a: Int =
                if (i < matrix.size - 1) findProductRecurse(matrix, i + 1, j, acc)
                else acc
            val b: Int =
                if (j < matrix.size - 1) findProductRecurse(matrix, i, j + 1, acc)
                else acc
            if (a > b) a else b
        }
    }

    /**
     * Dynamic style solution => O(n^2), where n = matrix dimension length
     */
    private fun findProductDynamic(matrix: List<List<Int>>): Int {

        val max: MutableList<MutableList<Int>> = MutableList(matrix.size) { MutableList(matrix.size) { 0 }}
        val min: MutableList<MutableList<Int>> = MutableList(matrix.size) { MutableList(matrix.size) { 0 }}

        for (i in matrix.indices) {
            for (j in matrix.indices) {
                // base case
                if (i == 0 && j == 0) {
                    max[i][j] = matrix[i][j]
                    min[i][j] = matrix[i][j]
                }
                // first row
                else if (i == 0) {
                    max[i][j] = max[i][j-1] * matrix[i][j]
                    min[i][j] = min[i][j-1] * matrix[i][j]
                }
                // first column
                else if (j == 0) {
                    max[i][j] = max[i-1][j] * matrix[i][j]
                    min[i][j] = min[i-1][j] * matrix[i][j]
                }
                // everything else - multiple paths
                else {
                    max[i][j] = Math.max(
                        Math.max(max[i-1][j] * matrix[i][j], max[i][j-1] * matrix[i][j]),
                        Math.max(min[i-1][j] * matrix[i][j], min[i][j-1] * matrix[i][j]))
                    min[i][j] = Math.min(
                        Math.min(max[i-1][j] * matrix[i][j], max[i][j-1] * matrix[i][j]),
                        Math.min(min[i-1][j] * matrix[i][j], min[i][j-1] * matrix[i][j]))
                }
            }
        }

        return max[matrix.size-1][matrix.size-1]
    }
}