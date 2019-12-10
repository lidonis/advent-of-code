import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.sqrt

fun main() {
    val input = InputReader("day10.txt").text
    val map = AsteroidMap(input)
    println(map.station())
    print(map.part2())
}

class AsteroidMap(private val asteroids: List<Coordinate>) {
    constructor(input: String) : this(input.split("\n").mapIndexed { i, line ->
        line.mapIndexedNotNull { j, c ->
            if (c == '#') Coordinate(j, i) else null
        }
    }.flatten())

    fun station(): Pair<Coordinate, Int>? = asteroids.map { it to candidateStation(it) } .maxBy { it.second }

    private fun candidateStation(station: Coordinate) =
        asteroids.filterNot { it == station }.map { it.angle(station) }.distinct().size

    fun part2(): Int {
        val laser = station()?.first ?: Coordinate(0,0)
        val rads = asteroids.filterNot { it == laser}
            .groupBy { laser.angle(it) + PI }
            .map { e -> e.key to e.value.sortedBy { ast -> laser.distance(ast) }.toMutableList() }
            .sortedByDescending { it.first }

        var p = Coordinate(0, 0)

        for (i in 0..199) {
            val list = rads[i % rads.size].second

            if (list.isNotEmpty()) {
                p = list.removeAt(0)
            }
        }

        return p.x * 100 + p.y
    }

    data class Coordinate(val x: Int, val y: Int) {
        fun angle(target: Coordinate) = atan2((target.x - x).toDouble(), (target.y - y).toDouble())
        fun distance(other: Coordinate) : Double = distance(this, other)

        companion object {
            fun distance(a: Coordinate, b: Coordinate): Double =
                sqrt(((b.y - a.y) * (b.y - a.y) + (b.x - a.x) * (b.x - a.x)).toDouble())
        }
    }
}
