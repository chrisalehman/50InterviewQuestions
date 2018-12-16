package string

import java.util.*


object K45AutoComplete {

    /**
     * Write an autocomplete class that returns all dictionary words with a given prefix.
     *
     * Eg.
     *      dict:   {"abc", "acd", "bcd", "def", "a", "aba"}
     *      prefix: "a" ‐> "abc", "acd", "a", "aba"
     *      prefix: "b" ‐> "bcd"
     */
    @JvmStatic
    fun main(args: Array<String>) {
        val t = Trie()
        t.insert("abc")
        t.insert("acd")
        t.insert("bcd")
        t.insert("def")
        t.insert("a")
        t.insert("aba")
        println(t.findMatches("a"))
        println(t.findMatches("b"))
    }

    /**
     * Trie data structure is used for finding matches to prefixes.
     *
     *      1. Time complexity: O(k + log(n)), where k = length of prefix, and n = number of nodes in trie
     *      2. Space complexity: O(n)
     */
    private class Trie {

        private val root: TrieNode = TrieNode()

        fun insert(word: String) {

            var curr = root
            val i = word.iterator()

            while (i.hasNext()) {

                val ch = i.next()

                val v = curr.children[ch]
                if (v == null) {
                    val child = TrieNode(ch, !i.hasNext())
                    curr.children[child.value] = child
                    curr = child
                }
                else
                    curr = v

                if (!i.hasNext())
                    curr.isEndOfWord = true
            }
        }

        fun findMatches(prefix: String): List<String> {

            var curr: TrieNode = root

            // traverse to end of prefix
            for (ch in prefix)
                curr = curr.children[ch] ?: return listOf()

            val trimmedPrefix = prefix.substring(0, prefix.length - 1)
            return collectMatches(trimmedPrefix, curr, mutableListOf())
        }

        private fun collectMatches(prefix: String, curr: TrieNode, accum: MutableList<String>): List<String> {
            if (curr.isEndOfWord)
                accum.add(prefix + curr.value)
            if (curr.children.isEmpty())
                return accum
            for ((_,v) in curr.children)
                collectMatches(prefix + curr.value, v, accum)
            return accum
        }
    }

    private data class TrieNode(val value: Char, var isEndOfWord: Boolean) {

        val children: MutableMap<Char,TrieNode> = HashMap()
        constructor(): this(Char.MIN_VALUE, false)

        override fun equals(other: Any?): Boolean = value == (other as TrieNode).value
        override fun hashCode(): Int = value.hashCode()
        override fun toString(): String = value.toString()
    }
}