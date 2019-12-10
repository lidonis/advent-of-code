import kotlin.math.abs

class AsteroidMap(val asteroids: List<Coordinate>) {

    constructor(input: String) : this(input.split("\n").mapIndexed { i, line ->
        line.mapIndexedNotNull { j, c ->
            if (c == '#') Coordinate(i, j) else null
        }
    }.flatten())

    fun bestMonitoringStation() =
        monitorings().maxBy { it.second }

    fun monitorings(): List<Pair<Coordinate, Int>> {
        return asteroids.map {
            it to monitor(it).count()
        }
    }

    fun monitor(station: Coordinate) =
        asteroids.filter { canSee(station, it, asteroids - station) }

    private fun canSee(
        station: Coordinate,
        target: Coordinate,
        list: List<Coordinate>
    ): Boolean {
        return if (station == target) {
            true
        } else if (station.x == target.x || station.y == target.y || abs(station.x - station.y) == abs(target.x - target.y)) {
            // TODO If diagonal or strait line, search obstacles
            return drawLine(station.x, station.y, target.x, target.y, list)
        } else {
            true
        }
    }

    private fun drawLine(
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int,
        list: List<Coordinate>
    ): Boolean { // delta of exact value and rounded value of the dependent variable
        var d = 0
        val dx = abs(x2 - x1)
        val dy = abs(y2 - y1)
        val dx2 = 2 * dx // slope scaling factors to
        val dy2 = 2 * dy // avoid floating point
        val ix = if (x1 < x2) 1 else -1 // increment direction
        val iy = if (y1 < y2) 1 else -1
        var x = x1
        var y = y1
        if (dx >= dy) {
            while (true) {
                if (x == x2 && y == y2) return true
                if (list.contains(Coordinate(x, y))) break
                if (x == x2) break
                x += ix
                d += dy2
                if (d > dx) {
                    y += iy
                    d -= dx2
                }
            }
        } else {
            while (true) {
                if (x == x2 && y == y2) return true
                if (list.contains(Coordinate(x, y))) break
                if (y == y2) break
                y += iy
                d += dx2
                if (d > dy) {
                    x += ix
                    d -= dy2
                }
            }
        }
        return false
    }

    data class Coordinate(val x: Int, val y: Int)
}
