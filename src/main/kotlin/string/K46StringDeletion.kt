package string

import java.util.*


object K46StringDeletion {

    /**
     * Given a string and a dictionary HashSet, write a function to determine the minimum number of characters
     * to delete to make a word.
     *
     * Eg.
     *      dictionary: [“a”, “aa”, “aaa”]
     *      query: “abc”
     *      output: 2
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val dict = HashSet<String>()
        dict.add("a")
        dict.add("aa")
        dict.add("aaa")

        println("delete(): expected=2, actual=" + delete("abc", dict))
    }

    /**
     * Uses a queue and a set. Remove one letter from word iteratively, add to queue, and keep checking if
     * substring exists in dictionary.
     *
     *      1. Time complexity: O(n!)
     *      2. Space complexity: O(n!)
     */
    private fun delete(query: String, dict: HashSet<String>): Int {

        val q: Queue<String> = LinkedList<String>()
        val qElements: MutableSet<String> = HashSet()

        q.add(query)
        while (!q.isEmpty()) {

            val word = q.poll()
            if (dict.contains(word))
                return query.length - word.length

            for (i in word.indices) {
                val reduced = word.substring(0,i) + word.substring(i+1, word.length)
                if (!reduced.isEmpty() && !qElements.contains(reduced)) {
                    q.add(reduced)
                    qElements.add(reduced)
                }
            }
        }
        return -1
    }
}