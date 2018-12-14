package recursion

import java.util.*

object K18LowestCommonAncestor {

    /**
     * Given two nodes in a binary tree (not BST), write a function to find the lowest common ancestor.
     *
     * Eg.
     *          1
     *        2   3
     *       4 5 6 7
     *
     *  Answer:
     *      lcs(4, 3) = 1
     *      lcs(6, 7) = 3
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val n4 = Node(4)
        val n5 = Node(5)
        val n6 = Node(6)
        val n7 = Node(7)
        val n2 = Node(2, n4, n5)
        val n3 = Node(3, n6, n7)
        val n1 = Node(1, n2, n3)

        println("lcs(4,3): expected=1, actual=${lcs(n1, n4, n3)}")
        println("lcs(6,7): expected=3, actual=${lcs(n1, n6, n7)}")

    }

    private data class Node(val value: Int, val left: Node?, val right: Node?) {
        constructor(value: Int) : this(value, null, null)
        override fun equals(other: Any?): Boolean = value == (other as Node).value
        override fun hashCode(): Int = value.hashCode()
        override fun toString(): String = value.toString()
    }

    /**
     * Find lcs by following algorithm:
     *      1. Build a bottom-up stack for each target node. Root node is on top for both.
     *      2. Iterate through stacks
     *          a. On match, cache value - this is a candidate (unless a lower one is found)
     *          b. On first non-match, stop searching
     *
     * Complexity:
     *      1. Time complexity: O(n + k); depth first traversal is O(n) time, then O(k) to iterate through ancestors
     *      2. Space complexity: O(k)
     */
    private fun lcs(root: Node, n1: Node, n2: Node): Node? {
        if (n1 == n2)
            return n1

        val n1Stack = findPath(root, n1.value) ?: return null
        val n2Stack = findPath(root, n2.value) ?: return null

        var prev: Node? = null
        while (!n1Stack.isEmpty() && !n2Stack.isEmpty()) {
            val n1Ancestor = n1Stack.pop()
            val n2Ancestor = n2Stack.pop()
            if (n1Ancestor == n2Ancestor)
                prev = n1Ancestor
            else
                break
        }

        return prev
    }

    private fun findPath(node: Node, target: Int): Stack<Node>? {
        if (node.value == target) {
            val stack = Stack<Node>()
            stack.push(node)
            return stack
        }
        if (node.left != null) {
            val leftStack = findPath(node.left, target)
            if (leftStack != null) {
                leftStack.push(node)
                return leftStack
            }
        }
        if (node.right != null) {
            val rightStack = findPath(node.right, target)
            if (rightStack != null) {
                rightStack.push(node)
                return rightStack
            }
        }
        return null
    }
}