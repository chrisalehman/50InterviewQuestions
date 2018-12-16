package string

import java.util.*


object K47LongestCommonSubstring {

    /**
     * Given two strings, write a function that returns the longest common substring.
     *
     * Eg.
     *      longestSubstring("ABAB", "BABAC") = "ABA"
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("longestSubstring(): expected=ABA, actual=" + longestSubstring("ABAB", "BABAC"))
    }

    private fun longestSubstring(s1: String, s2: String): String {

        val src = if (s1.length - s2.length < 0) s1 else s2
        val tgt = if (s1.length - s2.length < 0) s2 else s1

        val q: Queue<String> = LinkedList<String>()
        val qElements: MutableSet<String> = HashSet()
        val srcLen = src.length
        var currLen = srcLen

        q.add(src)
        qElements.add(src)

        while (!q.isEmpty()) {

            val word = q.poll()
            if (tgt.contains(word, false))
                return word

            currLen -= 1
            for (i in 0..(srcLen - currLen)) {
                val substr = word.substring(i, currLen)
                if (!qElements.contains(substr)) {
                    q.add(substr)
                    qElements.add(substr)
                }
            }
        }

        return ""
    }
}