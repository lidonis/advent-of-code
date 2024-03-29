package fr.lidonis.adventofcode.y2019

import fr.lidonis.adventofcode.common.ResourceReader
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.graph.BreadthFirstSearch
import fr.lidonis.adventofcode.y2019.Day18.Tile.Entrance
import fr.lidonis.adventofcode.y2019.Day18.Tile.Key
import fr.lidonis.adventofcode.y2019.Day18.Tile.TileFactory

fun main() {
    val input = ResourceReader("/input/y2019/day18.txt")?.text() ?: error("Input not found")
    val vault = Day18(input)
    println(vault.shortestPathStepCount())
}

class Day18(input: String) {

    private val vaultMap = mutableMapOf<Position, Tile>()
    private val keys = mutableSetOf<Key>()
    private val entrances = mutableSetOf<Position>()

    init {
        input
            .lines()
            .forEachIndexed { j, s ->
                s.forEachIndexed { i, c ->
                    val tile = TileFactory.from(c)
                    val position = Position(i, j)
                    vaultMap[position] = tile
                    if (tile is Key) {
                        keys.add(tile)
                    } else if (tile is Entrance) entrances.add(position)
                }
            }
    }

    fun shortestPathStepCount(): Int? {
        val nodeStart = Node(entrances.first())
        return BreadthFirstSearch.search(nodeStart, { it.keys.size == keys.size }, ::getNeighbours)
    }

    private fun getNeighbours(node: Node) = node.position.neighbours()
        .filter { vaultMap[it]?.access(node.keys) ?: false }
        .map {
            val keys = node.keys.toMutableSet()
            val tile = vaultMap[it]!!
            if (tile is Key) {
                keys += tile
            }
            Node(it, keys)
        }

    data class Node(val position: Position, val keys: Set<Key> = emptySet())

    sealed class Tile {
        abstract fun access(keys: Set<Key>): Boolean

        data object OpenPassage : Tile() {
            override fun access(keys: Set<Key>) = true
        }

        data object StoneWall : Tile() {
            override fun access(keys: Set<Key>) = false
        }

        data object Entrance : Tile() {
            override fun access(keys: Set<Key>) = true
        }

        data class Key(private val letter: Char) : Tile() {
            override fun access(keys: Set<Key>) = true
        }

        data class Door(private val letter: Char) : Tile() {
            private val key = Key(letter.lowercaseChar())
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
