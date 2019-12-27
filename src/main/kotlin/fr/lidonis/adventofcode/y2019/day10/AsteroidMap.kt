package fr.lidonis.adventofcode.y2019.day10

import Position

class AsteroidMap(private val asteroids: List<Position>) {

    constructor(input: String) : this(input.lines().mapIndexed { i, line ->
        line.mapIndexedNotNull { j, c ->
            if (c == '#') Position(j, i) else null
        }
    }.flatten())

    fun bestStation(): Pair<Position, Int>? = asteroids.map { it to countDetections(it) }.maxBy { it.second }

    private fun countDetections(station: Position) =
        asteroids.filterNot { it == station }.map { it.angle(station) }.distinct().size

    fun vaporize(nth: Int): Position {
        val laser = bestStation()?.first ?: Position(0, 0)
        val rads = asteroids.filterNot { it == laser }
            .groupBy { laser.angle(it) }
            .map { e -> e.key to e.value.sortedBy { ast -> laser.distance(ast) }.toMutableList() }
            .sortedByDescending { it.first }

        var asteroid = Position(0, 0)

        var i = 0
        var count = 0
        while (count < nth) {
            val list = rads[i % rads.size].second
            if (list.isNotEmpty()) {
                asteroid = list.removeAt(0)
                count++
            }
            i++
        }

        return asteroid
    }

}