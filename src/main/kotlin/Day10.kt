import kotlin.math.atan2

fun main() {
    val input = InputReader("day10.txt").text
    val map = AsteroidMap(input)
    print(map.bestMonitoringStation())
}

class AsteroidMap(private val asteroids: List<Coordinate>) {
    constructor(input: String) : this(input.split("\n").mapIndexed { i, line ->
        line.mapIndexedNotNull { j, c ->
            if (c == '#') Coordinate(i, j) else null
        }
    }.flatten())

    fun bestMonitoringStation() = monitorings().max()

    private fun monitorings() = asteroids.map { monitor(it) }

    private fun monitor(station: Coordinate) =
        asteroids.filterNot { it == station }.map { it.angle(station) }.distinct().size

    data class Coordinate(val x: Int, val y: Int) {
        fun angle(target: Coordinate) = atan2((target.x - x).toDouble(), (target.y - y).toDouble())
    }
}
