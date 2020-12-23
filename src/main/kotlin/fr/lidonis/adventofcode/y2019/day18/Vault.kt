package fr.lidonis.adventofcode.y2019.day18

import fr.lidonis.adventofcode.common.InputReader
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.graph.BreadthFirstSearch
import fr.lidonis.adventofcode.y2019.day18.Vault.Tile.Entrance
import fr.lidonis.adventofcode.y2019.day18.Vault.Tile.Key
import fr.lidonis.adventofcode.y2019.day18.Vault.Tile.TileFactory

fun main() {
    val input = InputReader("/input/y2019/day18.txt").lines()
    val vault = Vault(input)
    println(vault.shortestPathStepCount())
}

class Vault(input: List<String>) {

    private val vaultMap: Map<Position, Tile>
    private val keys: Set<Key>
    private val entrances: Set<Position>

    private val neighbours = mutableMapOf<Node, List<Node>>()


    init {
        vaultMap = mutableMapOf()
        keys = mutableSetOf()
        entrances = mutableSetOf()
        input.forEachIndexed { j, s ->
            s.forEachIndexed { i, c ->
                val tile = TileFactory.from(c)
                val position = Position(i, j)
                vaultMap[position] = tile
                if (tile is Key) keys.add(tile)
                else if (tile is Entrance) entrances.add(position)
            }
        }
    }

    fun shortestPathStepCount(): Int? {
        val nodeStart = Node(entrances.first())
        return BreadthFirstSearch.search(nodeStart, { it.keys.size == keys.size }, ::getNeighbours)
    }

    private fun getNeighbours(node: Node) = neighbours.getOrPut(node) {
        node.position.neighbours()
            .filter { vaultMap[it]?.access(node.keys) ?: false }
            .map {
                val keys = node.keys.toMutableSet()
                val tile = vaultMap[it]!!
                if (tile is Key)
                    keys += tile
                Node(it, keys)
            }
    }

    private data class Node(val position: Position, val keys: Set<Key> = emptySet())

    private sealed class Tile {
        abstract fun access(keys: Set<Key>): Boolean

        object OpenPassage : Tile() {
            override fun access(keys: Set<Key>) = true
        }

        object StoneWall : Tile() {
            override fun access(keys: Set<Key>) = false
        }

        object Entrance : Tile() {
            override fun access(keys: Set<Key>) = true
        }

        data class Key(private val letter: Char) : Tile() {
            override fun access(keys: Set<Key>) = true
        }

        data class Door(private val letter: Char) : Tile() {
            private val key = Key(letter.toLowerCase())
            override fun access(keys: Set<Key>) = key in keys
        }

        object TileFactory {
            fun from(c: Char) =
                when (c) {
                    '.' -> OpenPassage
                    '#' -> StoneWall
                    '@' -> Entrance
                    in 'a'..'z' -> Key(c)
                    in 'A'..'Z' -> Door(c)
                    else -> error("Unknown tile")
                }
        }
    }
}
