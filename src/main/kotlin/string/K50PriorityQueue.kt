package string


object K50PriorityQueue {

    @JvmStatic
    fun main(args: Array<String>) {

        val pq = MaxPriorityQueue(20)
        pq.push(1)
        pq.push(2)
        pq.push(3)
        pq.push(10)
        pq.push(4)
        pq.push(20)
        pq.push(5)

        println("pop(): expected=20, actual=" + pq.pop())
        println("pop(): expected=10, actual=" + pq.pop())
        println("pop(): expected=5, actual=" + pq.pop())
    }

    private class MaxPriorityQueue(maxCapcity: Int) {

        private var heap: IntArray = IntArray(maxCapcity) { 0 }
        private var size: Int = 0

        fun push(value: Int) {

            if (size == heap.size)
                throw IllegalStateException("Reached max capacity")

            var pos: Int = size
            heap[pos] = value

            while (pos > 0) {
                val parent: Int = ((pos + 1) / 2) - 1
                if (heap[parent] < value)
                    swap(heap, parent, pos)
                pos = parent
            }

            size++
        }

        fun pop(): Int {

            if (size == 0)
                throw IllegalStateException("Priority Queue is empty")

            val value = heap[0]
            heap[0] = heap[size-1]
            heap[size-1] = 0
            size--

            var pos = 0
            while (pos < size / 2) {

                val leftChild: Int = pos * 2 + 1
                val rightChild: Int = leftChild + 1
                val child = if (heap[leftChild] > heap[rightChild]) leftChild else rightChild

                if (heap[child] > heap[pos])
                    swap(heap, child, pos)
                pos = child
            }

            return value
        }

        private fun swap(heap: IntArray, a: Int, b: Int) {
            val tmp = heap[a]
            heap[a] = heap[b]
            heap[b] = tmp
        }

    }
}