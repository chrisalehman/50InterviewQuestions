package recursion


object K25BSTVerification {

    /**
     * Given a binary tree, write a function to test if the tree is a binary search tree.
     *
     * Eg.
     *    Valid:
     *                  5
     *                2   7
     *               1 3 6 8
     *    Invalid:
     *                  5
     *                2   7
     *               1 3 6 4
     */
    @JvmStatic
    fun main(args: Array<String>) {

        fun buildBST(): Node {

            val n1 = Node(1)
            val n3 = Node(3)
            val n2 = Node(2, n1, n3)

            val n6 = Node(6)
            val n8 = Node(8)
            val n7 = Node(7, n6, n8)

            return Node(5, n2, n7)
        }

        fun buildNonBST(): Node {

            val n1 = Node(1)
            val n3 = Node(3)
            val n2 = Node(2, n1, n3)

            val n6 = Node(6)
            val n4 = Node(4)
            val n7 = Node(7, n6, n4)

            return Node(5, n2, n7)
        }

        println("isBST(): expected=true, actual=" + isBST(buildBST()))
        println("isBST(): expected=false, actual=" + isBST(buildNonBST()))
    }

    private data class Node(val value: Int, val left: Node?, val right: Node?) {
        constructor(value: Int): this(value, null, null)
    }

    /**
     * Basic recursion implementation.
     *
     *      1. Time complexity: O(n)
     *      2. Space complexity: O(1)
     *
     * Perfectly good, slightly less elegant.
     */
    private fun isBST_personal(tree: Node?): Boolean {
        if (tree == null)
            return true
        val lBalanced = isBST(tree.left)
        val rBalanced = isBST(tree.right)
        if (!lBalanced || !rBalanced)
            return false
        val lValue = tree.left?.value ?: tree.value - 1
        val rValue = tree.right?.value ?: tree.value + 1
        return lValue - rValue < 0
    }

    private fun isBST(tree: Node?): Boolean {
        return isBST(tree, Int.MIN_VALUE, Int.MAX_VALUE)
    }

    private fun isBST(tree: Node?, min: Int, max: Int): Boolean {
        if (tree == null)
            return true
        if (tree.value < min || tree.value > max)
            return false
        val lBalanced = isBST(tree.left, min, tree.value)
        val rBalanced = isBST(tree.right, tree.value, max)
        return lBalanced && rBalanced
    }
}