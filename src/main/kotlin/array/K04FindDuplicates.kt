package array

import java.util.*

object K04FindDuplicates {

    /**
     * Given an array of integers where each value 1 <= x <= len(array), write a function that finds all
     * the duplicates in the array.
     *
     * Eg.
     *  dups([1, 2, 3]) = []
     *  dups([1, 2, 2]) = [2]
     *  dups([3, 3, 3]) = [3]
     *  dups([2, 1, 2, 1]) = [1, 2]
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("findDuplicates(): expected=[1, 2], actual=" + findDuplicates(
            listOf(
                2,
                1,
                2,
                1
            )
        )
        )
        println("findDuplicatesEncoded(): expected=[1, 2], actual=" + findDuplicatesEncoded(
            mutableListOf(2, 1, 2, 1)
        )
        )
    }

    /**
     * With map and set:
     *      1. O(n) time complexity
     *      2. O(n) space complexity
     */
    private fun findDuplicates(list: List<Int>): Set<Int> {

        val freq: MutableMap<Int,Int> = TreeMap()
        val dups: MutableSet<Int> = mutableSetOf()

        for (i in list)
            freq[i] = (freq[i] ?: 0) + 1
        for ((k,v) in freq)
            if (v > 1)
                dups.add(k)

        return dups
    }

    /**
     * Encoded into array itself:
     *      1. O(n) time complexity
     *      2. O(1) space complexity
     */
    private fun findDuplicatesEncoded(list: MutableList<Int>): Set<Int> {

        val dups: MutableSet<Int> = TreeSet()

        for (item in list) {
            val i = Math.abs(item)
            if (list[i] > 0)
                list[i] = list[i] * -1
            else {
                dups.add(i)
            }
        }

        return dups
    }
}