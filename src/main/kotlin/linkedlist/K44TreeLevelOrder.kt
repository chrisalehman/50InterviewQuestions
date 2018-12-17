package linkedlist

import java.util.*


object K44TreeLevelOrder {

    /**
     * Given a tree, write a function that prints out the nodes of the tree in level order.
     *
     * Eg.
     *          1
     *        2   3
     *       4 5 6 7
     *
     *      => traverseTree() = 1,2,3,4,5,6,7
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val n4 = Node(4)
        val n5 = Node(5)
        val n2 = Node(2, n4, n5)

        val n6 = Node(6)
        val n7 = Node(7)
        val n3 = Node(3, n6, n7)

        val n1 = Node(1, n2, n3)

        println("traverseTreeLevelOrder(): expected=[1, 2, 3, 4, 5, 6, 7], actual=" + traverseTreeLevelOrder(n1, mutableListOf()))
        println("traverseInOrder(): expected=[4, 2, 5, 1, 6, 3, 7], actual=" + traverseInOrder(n1, mutableListOf()))
    }

    private data class Node(val value: Int, val left: Node?, val right: Node?) {
        constructor(value: Int): this(value, null, null)
        fun isLeaf(): Boolean = left == null && right == null
        override fun toString(): String = value.toString()
    }

    private fun traverseTreeLevelOrder(tree: Node, accum: MutableList<Int>): List<Int> {

        val q: Queue<Node> = LinkedList()
        q.add(tree)

        while (!q.isEmpty()) {

            val curr = q.poll()
            accum.add(curr.value)

            if (curr.left != null)
                q.add(curr.left)
            if (curr.right != null)
                q.add(curr.right)
        }

        return accum
    }

    private fun traverseInOrder(tree: Node, accum: MutableList<Int>): List<Int> {
        if (tree.isLeaf()) {
            accum.add(tree.value)
            return accum
        }

        if (tree.left != null)
            traverseInOrder(tree.left, accum)
        accum.add(tree.value)
        if (tree.right != null)
            traverseInOrder(tree.right, accum)

        return accum
    }
}