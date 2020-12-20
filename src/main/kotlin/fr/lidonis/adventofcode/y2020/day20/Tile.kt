package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.math.pow
import fr.lidonis.adventofcode.common.tail

private val TILE_ID_REGEX = Regex("Tile (\\d+):")
private const val SIZE = 10

data class Tile(val id: Int, val positions: Set<Position>) {

    private val top by lazy { List(SIZE) { i -> positions.filter { it.x == 0 }.contains(Position(0, i)) } }
    private val right by lazy {
        List(SIZE) { i ->
            positions.filter { it.y == SIZE - 1 }.contains(Position(i, SIZE - 1))
        }
    }
    private val bottom by lazy {
        List(SIZE) { i ->
            positions.filter { it.x == SIZE - 1 }.contains(Position(SIZE - 1, i))
        }
    }
    private val left by lazy { List(SIZE) { i -> positions.filter { it.y == 0 }.contains(Position(i, 0)) } }

    private val scores by lazy {
        setOf(
            top.mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it },
            top.reversed().mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it },

            right.mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it },
            right.reversed().mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it },

            bottom.mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it },
            bottom.reversed().mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it },

            left.mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it },
            left.reversed().mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it }
        )
    }

    fun match(otherTile: Tile): Boolean {
        return (scores intersect otherTile.scores).isNotEmpty()
    }

    constructor(lines: List<String>) : this(
        TILE_ID_REGEX.matchEntire(lines.head)?.destructured?.component1()?.toInt() ?: error("No tile id found"),
        sequence {
            for ((i, line) in lines.tail.withIndex()) {
                for ((j, c) in line.withIndex()) {
                    if (c == '#') yield(Position(i, j))
                }
            }
        }.toSet()
    )
}