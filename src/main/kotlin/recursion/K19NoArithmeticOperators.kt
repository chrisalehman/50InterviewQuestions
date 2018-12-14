package recursion


object K19NoArithmeticOperators {

    /**
     * Add or subtract two numbers without using any arithmetic operators.
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("add(11,24): expected=35, actual=${add(11,24)}")
        println("subtract(24,11): expected=13, actual=${subtract(24,11)}")
    }

    private fun add(a: Int, b: Int): Int {
        if (b == 0) return a
        val partial = a xor b
        val carryover = (a and b) shl 1
        return add(partial, carryover)
    }

    private fun subtract(a: Int, b: Int): Int {
        if (b == 0) return a
        val partial = a xor b
        val carryover = (a.inv() and b) shl 1
        return subtract(partial, carryover)
    }
}