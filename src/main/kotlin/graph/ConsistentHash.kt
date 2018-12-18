package graph

import java.util.*
import java.math.BigInteger
import java.security.MessageDigest


class ConsistentHash(val replicas: Int, nodes: List<String>) {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val ch = ConsistentHash(512, listOf("host1", "host2", "host3", "host4"))
            println("bird => ${ch.get("bird")}")
            println("bobcat => ${ch.get("bobcat")}")
            println("eagle => ${ch.get("eagle")}")
            println("elephant => ${ch.get("elephant")}")
            println("giraffe => ${ch.get("giraffe")}")
            println("hippo => ${ch.get("hippo")}")
            println("leopard => ${ch.get("leopard")}")
            println("lion => ${ch.get("lion")}")
            println("lynx => ${ch.get("lynx")}")
            println("zebra => ${ch.get("zebra")}")
        }
    }

    private val ring: SortedMap<BigInteger,String> = TreeMap()

    init {
        for (node in nodes)
            add(node)
    }

    fun add(node: String) {
        for (i in 0..(replicas-1)) {
            val hash = hash("$node$i")
            ring[hash] = node
        }
    }

    fun remove(node: String) {
        for (i in 0..(replicas-1)) {
            val hash = hash("$node$i")
            ring.remove(hash)
        }
    }

    fun get(key: Any): String? {
        if (ring.isEmpty())
            return null
        var hash = hash(key.toString())
        if (!ring.containsKey(hash)) {
            val tailMap = ring.tailMap(hash)
            hash = if (tailMap.isEmpty()) ring.firstKey() else tailMap.firstKey()
        }
        return ring[hash]
    }

    private fun hash(input: String): BigInteger {
        val md = MessageDigest.getInstance("MD5")
        val messageDigest = md.digest(input.toByteArray())
        return BigInteger(1, messageDigest)
    }
}