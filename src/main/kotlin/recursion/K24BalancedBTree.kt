package recursion


object K24BalancedBTree {

    /**
     * Given a binary tree, write a function to determine whether the tree is balanced (+/- 1 depth).
     *
     * Eg, balanced:
     *                    1
     *             2             3
     *           4   5       6       7
     *          8         9    10  11  12
     *                     13
     *
     * Eg, unbalanced:
     *                    1
     *             2             3
     *           4   5       6       7
     *          8         9    10  11  12
     *                     13
     *                      14
     */
    @JvmStatic
    fun main(args: Array<String>) {
        println("isBalanced2(): expected=true, actual=" + isBalanced2(buildBalancedTree()))
        println("isBalanced2(): expected=false, actual=" + isBalanced2(buildUnbalancedTree()))
    }

    private fun buildBalancedTree(): Node {

        val n8 = Node(8)
        val n4 = Node(4, n8, null)
        val n5 = Node(5)
        val n2 = Node(2, n4, n5)

        val n13 = Node(13)
        val n9 = Node(9, null, n13)
        val n10 = Node(10)
        val n6 = Node(6, n9, n10)

        val n11 = Node(11)
        val n12 = Node(12)
        val n7 = Node(7, n11, n12)

        val n3 = Node(3, n6, n7)
        val n1 = Node(1, n2, n3)

        return n1
    }

    private fun buildUnbalancedTree(): Node {

        val n8 = Node(8)
        val n4 = Node(4, n8, null)
        val n5 = Node(5)
        val n2 = Node(2, n4, n5)

        val n14 = Node(14)
        val n13 = Node(13, n14, null)
        val n9 = Node(9, null, n13)
        val n10 = Node(10)
        val n6 = Node(6, n9, n10)

        val n11 = Node(11)
        val n12 = Node(12)
        val n7 = Node(7, n11, n12)

        val n3 = Node(3, n6, n7)
        val n1 = Node(1, n2, n3)

        return n1
    }

    private data class Node(val value: Int, val left: Node?, val right: Node?) {
        constructor(value: Int): this(value, null, null)
        fun isLeaf(): Boolean = left == null && right == null
        override fun toString(): String = value.toString()
    }

    private data class SubTree(var height: Int, var isBalanced: Boolean)

    /**
     * Recursive depth first traversal. At each node, asks whether left and right sub-trees are balanced.
     *
     *      1. Time complexity: O(n)
     *      2. Space complexity: O(1)
     */
    private fun isBalanced(node: Node): SubTree {
        if (node.isLeaf()) {
            return SubTree(1, true)
        }

        val left = if (node.left != null) isBalanced(node.left) else SubTree(0, true)
        if (!left.isBalanced)
            return left

        val right = if (node.right != null) isBalanced(node.right) else SubTree(0, true)
        if (!right.isBalanced)
            return right

        val diff = left.height - right.height
        val maxHeight = if (diff < 0) right.height else left.height
        return SubTree(maxHeight + 1, Math.abs(diff) < 2)
    }

    /**
     * Slightly easier to understand. Same time/space complexity.
     */
    private fun isBalanced2(node: Node): Boolean {
        return balancedHeight(node) > -1
    }

    private fun balancedHeight(node: Node?): Int {
        if (node == null)
            return 0
        val h1 = balancedHeight(node.left)
        val h2 = balancedHeight(node.right)
        if (h1 == -1 || h2 == -1)
            return -1
        if (Math.abs(h1 - h2) > 1)
            return -1
        return if (h1 > h2) h1 + 1
            else h2 + 1
    }
}