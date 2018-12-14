package array

import java.util.*

object K08MergeKArrays {

    /**
     * Given k sorted arrays, merge them into a single sorted array.
     *
     * Ex.
     *      merge({{1, 4, 7},{2, 5, 8},{3, 6, 9}}) = {1, 2, 3, 4, 5, 6, 7, 8, 9}
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val input = listOf(
            listOf(1,5,7),
            listOf(2,4,8),
            listOf(3,6,9))

        println("merge(): expected=[1, 2, 3, 4, 5, 6, 7, 8, 9], actual=" + merge(input))
        println("mergeTrivial(): actual=" + mergeTrivial(input))
        println("mergePriorityQ(): actual=" + mergePriorityQ(input))
    }

    /**
     * No sort:
     *      1. O(m^2 x n) time complexity
     *      2. O(1) space complexity
     *
     * Worst!!!!
     */
    private fun merge(lists: List<List<Int>>): List<Int> {

        val merged: MutableList<Int> = mutableListOf()
        val counters: MutableList<Int> = MutableList(lists.size) { 0 }
        val maxSize = lists.size * lists[0].size

        while (merged.size < maxSize) {
            var min = Int.MAX_VALUE
            var list = -1
            for (i in counters.indices) {
                if (counters[i] < lists[i].size) {
                    val next = lists[i][counters[i]]
                    if (next < min) {
                        min = next
                        list = i
                    }
                }
            }
            merged.add(min)
            counters[list] += 1
        }

        return merged
    }

    /**
     * Flatten, sort:
     *      1. O(mn log(mn)) time complexity
     *      2. O(1) space complexity
     *
     * Not bad, but we can do better.
     */
    private fun mergeTrivial(lists: List<List<Int>>): List<Int> {
        return lists
            .flatten()
            .sorted()
    }

    /**
     * Priority queue:
     *      1. O(mn * log(m)) - mn to go through all values, log(m) for priority queue add (pop is constant)
     *      2. O(m) space complexity
     */
    private fun mergePriorityQ(lists: List<List<Int>>): List<Int> {

        data class Node(val list: Int, val index: Int, val value: Int) : Comparable<Node> {
            override fun compareTo(other: Node): Int {
                return value - other.value
            }
        }

        val merged: MutableList<Int> = mutableListOf()
        val counters: MutableList<Int> = MutableList(lists.size) { 1 }

        // initialize priority q
        val q: PriorityQueue<Node> = PriorityQueue()
        for (i in lists.indices) {
            q.add(Node(i, 0, lists[i][0]))
        }

        // iterate until priority q is depleted
        while (!q.isEmpty()) {
            val next = q.poll()
            merged.add(next.value)

            // only increment index and replenish priority q if list is not depleted
            if (next.index+1 < lists[next.list].size) {
                val node = Node(next.list, next.index + 1, lists[next.list][next.index+1])
                counters[next.list] += 1
                q.add(node)
            }
        }

        return merged
    }
}