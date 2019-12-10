import java.awt.Graphics


class AsteroidMap(input: List<String>) {
    private val asteroids = input.mapIndexed { i, line ->
        line.mapIndexedNotNull { j, c ->
            if (c == '#') Coordinate(i, j) else null
        }
    }.flatten()

    private val maxX = input[0].length
    private val maxY = input.size

    fun bestMonitoringStation() =
        asteroids.map {
            monitor(it)
        }.max()

    private fun monitor(station: Coordinate) =
        asteroids.filter { canSee(station, it, asteroids - station) }.count()


    private fun canSee(
        station: Coordinate,
        target: Coordinate,
        list: List<Coordinate>
    ): Boolean {
        return if (station == target) {
            true
        }
        else drawLine(station.x, station.y, target.x, target.y, list)
    }

    private fun drawLine(
        x1: Int,
        y1: Int,
        x2: Int,
        y2: Int,
        list: List<Coordinate>
    ): Boolean { // delta of exact value and rounded value of the dependent variable
        var d = 0
        val dx = Math.abs(x2 - x1)
        val dy = Math.abs(y2 - y1)
        val dx2 = 2 * dx // slope scaling factors to
        val dy2 = 2 * dy // avoid floating point
        val ix = if (x1 < x2) 1 else -1 // increment direction
        val iy = if (y1 < y2) 1 else -1
        var x = x1
        var y = y1
        if (dx >= dy) {
            while (true) {
                if(x == x2 && y == y2) return true
                if(list.contains(Coordinate(x, y))) return true
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
                if(x == x2 && y == y2) return true
                if(list.contains(Coordinate(x, y))) return false
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
