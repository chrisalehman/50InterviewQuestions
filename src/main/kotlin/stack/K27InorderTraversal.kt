package stack

import java.util.*


object K27InorderTraversal {

    /**
     * Given a binary search tree, print out the elements of the tree in order *without* using recursion.
     *
     * Eg.
     *              5
     *           2    7
     *          1 3  6 8
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

        println("inorderTraversal()...")
        inorderTraversal(buildBST())
    }

    private data class Node(val value: Int, val left: Node?, val right: Node?) {
        constructor(value: Int): this(value, null, null)
    }

    /**
     * Inorder traversal using a stack.
     *
     *      1. Time complexity: O(n)
     *      2. Space complexity: O(log n)
     */
    private fun inorderTraversal(tree: Node) {

        val stack = Stack<Node>()
        var curr: Node? = tree

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr)
                curr = curr.left
            }
            curr = stack.pop()
            println(curr.value)
            curr = curr.right
        }
    }
}