package array


object K14Anagram {

    /**
     * Given two strings, write a function to determine whether they are anagrams.
     *
     * Eg.
     *      isAnagram("", "") = true
     *      isAnagram("A", "A") = true
     *      isAnagram("A", "B") = false
     *      isAnagram("ab", "ba") = true
     *      isAnagram("AB", "ab") = true
     *      isAnagram("ABcdEf", "fCdbae") = true
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("isAnagram(abcdef, FEDCBA): expected=true, actual=${isAnagram("abcdef", "FEDCBA")}")
        println("isAnagram(abcdef, FEDCBA): expected=false, actual=${isAnagram("abcdef", "FEDCBAa")}")
        println("isAnagram2(abcdef, FEDCBA): expected=true, actual=${isAnagram2("abcdef", "FEDCBA")}")
        println("isAnagram2(abcdef, FEDCBA): expected=false, actual=${isAnagram2("abcdef", "FEDCBAa")}")
    }

    /**
     * Using sets (my solution):
     *      1. O(n) time complexity
     *      2. O(n) space complexity
     *
     * Mine seems perfectly sufficient.
     */
    private fun isAnagram(a: String, b: String): Boolean {
        if (a.length != b.length)
            return false
        if (a.isEmpty() && b.isEmpty())
            return true

        val aSet = HashSet<Char>()
        val bSet = HashSet<Char>()

        for (i in a.indices) {
            val aCh = a[i].toLowerCase()
            val bCh = b[i].toLowerCase()
            if (aCh == bCh)
                continue
            if (!aSet.remove(bCh))
                bSet.add(bCh)
            if (!bSet.remove(aCh))
                aSet.add(aCh)
        }

        return aSet.size + bSet.size == 0
    }

    /**
     * Using Int list:
     *      1. O(n) time complexity (although takes 2 iterations instead of 1)
     *      2. O(n) space complexity
     */
    private fun isAnagram2(a: String, b:String): Boolean {
        if (a.length != b.length)
            return false
        if (a.isEmpty() && b.isEmpty())
            return true

        val chars = MutableList(256) { 0 }

        for (i in a.indices) {
            val aCh = a[i].toLowerCase()
            val bCh = b[i].toLowerCase()
            if (aCh == bCh)
                continue
            chars[aCh.toInt()] += 1     // add 1 for a
            chars[bCh.toInt()] -= 1     // sub 1 for b
        }

        // if anagram, all indexes should be zero
        for (count in chars) {
            if (count != 0)
                return false
        }

        return true
    }
}