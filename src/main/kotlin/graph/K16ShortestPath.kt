package graph

import java.util.*


object K16ShortestPath {

    /**
     * Given a directed graph, find the shortest path between two nodes if one exists.
     *
     * Eg.
     *      1->2->5->4->3
     *      1->3     4->1
     *
     *      Answer: shortestPath(2, 3) = 2 ‐> 5 ‐> 4 ‐> 3
     */
    @JvmStatic
    fun main(args: Array<String>) {

        val n1 = Node(1)
        val n2 = Node(2)
        val n3 = Node(3)
        val n4 = Node(4)
        val n5 = Node(5)
        n1.add(n2)
        n1.add(n3)
        n2.add(n5)
        n5.add(n4)
        n4.add(n3)
        n4.add(n1)

        println("shortestPath(2,3): expected=[2->5->4->3], actual=${shortestPath(n2, n3)}")
    }

    private data class Node(val value: Int, var visited: Boolean, private val next: MutableList<Node>) {
        constructor(value: Int) : this(value, false, mutableListOf())

        fun add(n: Node) = next.add(n)
        fun children(): List<Node> = next
        override fun toString(): String = value.toString()
    }

    private class Path private constructor() : Comparable<Path>{

        private val nodes: MutableList<Node> = mutableListOf()

        constructor(start: Node, next: Node) : this() {
            nodes.add(start)
            nodes.add(next)
        }
        constructor(nodes: MutableList<Node>) : this () {
            this.nodes.addAll(nodes)
        }

        fun first() = nodes.first()
        fun last() = nodes.last()
        fun append(n: Node): Path {
            val clone = nodes.toMutableList()
            clone.add(n)
            return Path(clone)
        }

        // shortest path sorts first
        override fun compareTo(other: Path): Int = nodes.size - other.nodes.size
        override fun toString(): String {
            val sb = StringBuffer("[")
            for (i in nodes.indices) {
                sb.append(nodes[i].value)
                if (i < nodes.size-1)
                    sb.append("->")
            }
            return sb.append("]").toString()
        }
    }

    /**
     * Implemented with a priority queue, the fastest way known to solve problem.
     *
     *      1. Time complexity: O(k + n log n), where k = number of segments, and n = number of nodes
     *      2. Space complexity: O(k)
     */
    private fun shortestPath(start: Node, end: Node): Path? {

        if (start.value == end.value)
            return null

        var shortest: Path? = null
        val pq = PriorityQueue<Path>()

        // init priority queue
        for(ch in start.children())
            pq.add(Path(start, ch))

        while (!pq.isEmpty()) {

            val path: Path = pq.poll()

            // found match
            if (path.first().value == start.value && path.last().value == end.value) {
                shortest = path
                break
            }

            // append segment to path and requeue
            for (ch in path.last().children()) {
                if (!ch.visited) {
                    ch.visited = true
                    pq.add(path.append(ch))
                }
            }
        }

        return shortest
    }
}