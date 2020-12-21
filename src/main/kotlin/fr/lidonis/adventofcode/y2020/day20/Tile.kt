package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.math.pow
import fr.lidonis.adventofcode.common.tail

private val TILE_ID_REGEX = Regex("Tile (\\d+):")
private const val SIZE = 10

data class Tile(val id: Int, val positions: Set<Position>) {

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

    private val top by lazy { line(0) }
    private val right by lazy { row(SIZE - 1) }
    private val bottom by lazy { line(SIZE - 1) }
    private val left by lazy { row(0) }
    private val topReversed by lazy { top.reversed() }
    private val rightReversed by lazy { right.reversed() }
    private val bottomReversed by lazy { bottom.reversed() }
    private val leftReversed by lazy { left.reversed()}

    private fun line(x: Int) = List(SIZE) { i -> positions.filter { it.x == x }.contains(Position(x, i)) }
    private fun row(y: Int) = List(SIZE) { i -> positions.filter { it.y == y }.contains(Position(i, y)) }

    private val scores by lazy {
        listOf(
            score(top), score(right), score(bottom), score(left),
            score(topReversed), score(rightReversed), score(bottomReversed), score(leftReversed),
        )
    }

    private fun score(list: List<Boolean>) = list.mapIndexedNotNull { i, b -> if (b) i else null }.sumBy { 2 pow it }

    fun isMatching(otherTile: Tile) = match(otherTile).isNotEmpty()

    private fun match(otherTile: Tile) = scores intersect otherTile.scores

    fun matchId(otherTile: Tile): Pair<Direction, Direction> {
        val match = match(otherTile).first()
        return Direction.UP.plus(scores.indexOf(match)) to Direction.UP.plus(otherTile.scores.indexOf(match))
    }

    fun display() {
        println("Tile $id:")
        for (i in 0 until SIZE) {
            for (j in 0 until SIZE) {
                print(if (positions.contains(Position(i, j))) "#" else ".")
            }
            println()
        }
    }




}
