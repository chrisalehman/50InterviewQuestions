package array

object K02Knapsack {

    /**
     * Find max value of combinations of items without exceeding max weight.
     *
     * Ex.
     *      items = {(w:1, v:6), (w:2, v:10), (w:3, v:12)}
     *      maxWeight = 5
     *      knapsackDynamic(items, maxWeight) = 22
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val items = listOf(
            Item(1, 6),
            Item(2, 10),
            Item(3, 12)
        )
        val maxWeight = 5

        println("Max value: " + knapsackRecurse(items, 0, maxWeight))
        println("Max value: " + knapsackDynamic(items, maxWeight))
    }

    private data class Item(val weight: Int, val value: Int)

    /**
     * Since there are n binary options (with/without), this recursive solution is O(n^2),
     * where n = number of items.
     */
    private fun knapsackRecurse(items: List<Item>, i: Int, maxWeight: Int): Int {
        return when {
            i >= items.size -> 0
            // skip any items that weigh more than max
            items[i].weight > maxWeight -> knapsackRecurse(items, i + 1, maxWeight)
            // here we choose the better performing of 'with' or 'without' options
            else -> Math.max(
                knapsackRecurse(items, i + 1, maxWeight - items[i].weight) + items[i].value,
                knapsackRecurse(items, i + 1, maxWeight)
            )
        }
    }

    /**
     * Only inputs that change are item (i) and maxWeight (j). This can be represented in the following table:
     *
     *    i= 0, 1, 2,  3
     *  j=0 [0, 0, 0,  0 ]
     *  j=1 [0, 6, 6,  6 ]
     *  j=2 [0, 6, 10, 10]
     *  j=3 [0, 6, 16, 16]
     *  j=4 [0, 6, 16, 18]
     *  j=5 [0, 6, 16, 22]
     *
     *  I don't fully understand this one... 
     */
    private fun knapsackDynamic(items: List<Item>, maxWeight: Int): Int {

        val matrix = MutableList(items.size + 1) { MutableList(maxWeight + 1) { 0 } }

        for (i in 1..items.size) {
            for (j in 0..maxWeight) {

                if (items[i - 1].weight > j) {
                    val value = matrix[i - 1][j]
                    matrix[i][j] = value
                }
                else {
                    val value1 = matrix[i - 1][j]
                    val value2 = matrix[i - 1][j - items[i - 1].weight] + items[i - 1].value
                    matrix[i][j] = Math.max(value1, value2)
                }
            }
        }

        return matrix[items.size][maxWeight]
    }
}