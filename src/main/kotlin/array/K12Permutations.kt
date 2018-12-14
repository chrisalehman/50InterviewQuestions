package array

object K12Permutations {

    /**
     * Write a function that returns all permutationsLoop of a given list.
     */
    @JvmStatic
    fun main(args: Array<String>) {

//        val start = now()
//        val permutations = permutationsLoop(listOf(1,2,3,4,5,6,7,8,9))
//        val stop = now()
//        val total = stop.toEpochMilli() - start.toEpochMilli()
//        println("permutationsLoop(): expected=720, actual=${permutations.size}")
//        println("Time: $total")
//        println(permutations)
//
//        val start2 = now()
//        val permutations2 = permutationsRecurse(mutableListOf(1,2,3,4,5,6,7,8,9), 0, MutableList(0) { listOf<Int>() })
//        val stop2 = now()
//        val total2 = stop2.toEpochMilli() - start2.toEpochMilli()
//        println("permutationsRecurse(): expected=720, actual=${permutations2.size}")
//        println("Time: $total2")
//        println(permutations2)

        val permutations3 = permutationsRecurseStr("", "abcdef", mutableListOf())
        println("permutationsRecurse(): expected=720, actual=${permutations3.size}")
        println(permutations3)
    }

    /**
     * While loop:
     *      1. Worse than O(n!) time complexity
     *      2. O(n!) space complexity
     *
     * Much slower than recursive examples! Iterates more times than necessary.
     */
    private fun permutationsLoop(list: List<Int>): Set<List<Int>> {

        val perms = mutableSetOf<List<Int>>()
        perms.add(list)

        for (i in list.indices) {

            val subPerms = mutableSetOf<List<Int>>()
            for (p in perms) {

                val perm = p.toMutableList()
                for (j in i..(p.size-1)) {
                    if (j != i) {
                        val tmp = perm[j]
                        perm[j] = perm[i]
                        perm[i] = tmp
                    }
                    subPerms.add(perm.toList())
                }
            }
            perms.addAll(subPerms)
        }

        return perms
    }

    /**
     * Standard recursive version:
     *      1. O(n!) space complexity
     *      2. O(n!) time complexity
     *
     * Study this!!
     */
    private fun permutationsRecurse(a: MutableList<Int>, start: Int, results: MutableList<List<Int>>): MutableList<List<Int>> {
        if (start >= a.size) {
            results.add(a.toList())
        }
        else {
            for (i in start..(a.size - 1)) {
                swap(a, start, i)
                permutationsRecurse(a, start + 1, results)
                swap(a, start, i)
            }
        }
        return results
    }

    /**
     * Standard recursive version for Strings:
     *      1. O(n!) time complexity
     *      2. O(n!) space complexity
     */
    private fun permutationsRecurseStr(prefix: String, suffix: String, results: MutableList<String>): MutableList<String> {
        if (suffix.isEmpty()) {
            results.add(prefix)
        }
        else {
            for (i in 0..(suffix.length-1)) {
                permutationsRecurseStr(
                    prefix + suffix[i],
                    suffix.substring(0, i) + suffix.substring(i + 1),
                    results
                )
            }
        }
        return results
    }

    private fun swap(list: MutableList<Int>, i: Int, j: Int) {
        val tmp = list[i]
        list[i] = list[j]
        list[j] = tmp
    }
}