import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.hypot
import kotlin.math.sqrt

fun main() {
    val input = InputReader("day10.txt").text
    val map = AsteroidMap(input)
    println(map.station())
    print(map.shootingSequence().elementAt(199))
}

class AsteroidMap(private val asteroids: List<Coordinate>) {
    constructor(input: String) : this(input.split("\n").mapIndexed { i, line ->
        line.mapIndexedNotNull { j, c ->
            if (c == '#') Coordinate(j, i) else null
        }
    }.flatten())

    fun station(): Pair<Coordinate, Int>? = asteroids.map { it to candidateStation(it) }.maxBy { it.second }

    private fun candidateStation(station: Coordinate) =
        asteroids.filterNot { it == station }.map { it.angle(station) }.distinct().size

    fun shootingSequence() = sequence {
        val laser = station()?.first
        requireNotNull(laser)
        val rads = asteroids.asSequence().filterNot { it == laser }
            .groupBy { laser.angle(it) }
            .map { e -> e.key to e.value.sortedBy { asteroid -> laser.distance(asteroid) }.toMutableList() }
            .sortedByDescending { it.first }
            .toMutableList()

        while (rads.isNotEmpty()) {
            val iterator = rads.iterator()
            while (iterator.hasNext()) {
                val elt = iterator.next()
                yield(elt.second[0])
                if (elt.second.size == 1) {
                    iterator.remove()
                } else {
                    elt.second.removeAt(0)
                }
            }
        }
    }

    data class Coordinate(val x: Int, val y: Int) {
        fun angle(target: Coordinate) = atan2((target.x - x).toDouble(), (target.y - y).toDouble())
        fun distance(other: Coordinate) = hypot((x - other.x).toDouble(), (y - other.y).toDouble())
    }
}
