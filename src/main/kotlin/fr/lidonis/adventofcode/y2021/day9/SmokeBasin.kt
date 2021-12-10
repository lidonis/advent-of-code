package fr.lidonis.adventofcode.y2021.day9

import fr.lidonis.adventofcode.common.geo.plane.Position

private const val LARGEST_BASIN_COUNT = 3
private const val HIGHEST_LOCATION = 9

class SmokeBasin(lines: List<String>) {

    private val smokes = lines.flatMapIndexed { j, line ->
        line.mapIndexed { i, num ->
            Position(i, j) to num.digitToInt()
        }
    }.toMap()

    fun sumRiskLowestPoints() = findLowestPoints().values.sumOf { it + 1 }

    private fun findLowestPoints() = smokes.filter { (pos, risk) ->
        pos.neighbours()
            .mapNotNull { smokes[it] }
            .all { it > risk }
    }

    fun findThreeLargestBasinsSize(): List<Int> = findLowestPoints().map {
        1 + neighbours(it.toPair()).size
    }.sortedDescending().take(LARGEST_BASIN_COUNT)

    fun neighbours(entry: Pair<Position, Int>): List<Pair<Position, Int>> = entry.first.neighbours()
        .mapNotNull { smokes[it]?.let { risk -> it to risk } }
        .filter { it.second > entry.second && it.second < HIGHEST_LOCATION }
        .flatMap { listOf(it) + neighbours(it) }.distinct()

}
