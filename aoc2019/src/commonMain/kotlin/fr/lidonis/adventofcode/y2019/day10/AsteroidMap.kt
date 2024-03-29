package fr.lidonis.adventofcode.y2019.day10

import fr.lidonis.adventofcode.common.geo.plane.Position

class AsteroidMap(private val asteroids: List<Position>) {

    constructor(input: String) : this(
        sequence {
            for ((i, s) in input.lines().withIndex()) {
                for ((j, c) in s.withIndex()) {
                    if (c == '#') yield(Position(j, i))
                }
            }
        }.toList()
    )

    fun bestStation(): Pair<Position, Int>? = asteroids.map { it to countDetections(it) }.maxByOrNull { it.second }

    private fun countDetections(station: Position) =
        asteroids.filterNot { it == station }.map { it.angle(station) }.distinct().size

    fun vaporize(nth: Int): Position? {
        require(nth < asteroids.size) { "Can't vaporize $nth asteroid. Only ${asteroids.size - 1} available " }
        val laser = bestStation()?.first ?: Position.ORIGIN
        val shootingMap = asteroids.asSequence()
            .filterNot { it == laser }
            .groupBy { laser.angle(it) }
            .map { e -> e.key to e.value.sortedBy { ast -> laser.distance(ast) }.toMutableList() }
            .sortedByDescending { it.first }
            .toMutableList()

        var asteroid: Position? = null

        var i = 0
        var count = 0
        while (count < nth) {
            val targets = shootingMap[i % shootingMap.size]
            if (targets.second.isNotEmpty()) {
                asteroid = targets.second.removeAt(0)
                count++
            } else {
                shootingMap.remove(targets)
            }
            i++
        }

        return asteroid
    }
}
