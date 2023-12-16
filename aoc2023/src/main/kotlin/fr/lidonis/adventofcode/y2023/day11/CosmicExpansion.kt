package fr.lidonis.adventofcode.y2023.day11

import fr.lidonis.adventofcode.common.combine
import fr.lidonis.adventofcode.common.geo.plane.Position

private const val PART_1_EXPANSION_RATE = 2
private const val PART_2_EXPANSION_RATE = 1000000

class CosmicExpansion(private val lines: List<String>) {

    private val image = getImage(lines)

    private fun getImage(strings: List<String>) = strings.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (c == '#') {
                Position(x, y)
            } else {
                null
            }
        }
    }

    private fun expand(expansionRate: Int): List<Position> {
        val emptyColumn = (0..lines.first().length).filter { i -> image.none { it.x == i } }
        val emptyRow = (0..lines.size).filter { i -> image.none { it.y == i } }
        return image.map { position ->
            val x = position.x
            val y = position.y
            Position(
                x = x + emptyColumn.count { it < x } * (expansionRate - 1),
                y = y + emptyRow.count { it < y } * (expansionRate - 1),
            )
        }
    }

    fun part1() = sumShortestPath(PART_1_EXPANSION_RATE)

    fun sumShortestPath(expansionRate: Int): Long {
        return expand(expansionRate)
            .combine(2)
            .sumOf { it[0].manhattanDistance(it[1]).toLong() }
    }

    fun part2() = sumShortestPath(PART_2_EXPANSION_RATE)
}
