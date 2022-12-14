package fr.lidonis.adventofcode.y2022.day14

import fr.lidonis.adventofcode.common.geo.plane.Position

private const val SOURCE_X = 500

class RegolithReservoir(private val lines: List<String>) {

    private val rocks = buildSet {
        for (line in lines) {
            line.split(" -> ")
                .map {
                    val (x, y) = it.split(",")
                    x.toInt() to y.toInt()
                }.zipWithNext { (x1, y1), (x2, y2) ->
                    for (x in minOf(x1, x2)..maxOf(x1, x2)) {
                        for (y in minOf(y1, y2)..maxOf(y1, y2)) {
                            add(Position(x, y))
                        }
                    }
                }
        }
    }
    private val maxY = rocks.maxOf { it.y }

    fun countSandBeforeAbyss(): Int {
        val units = rocks.toMutableSet()
        return generateSequence {
            units.fall(maxY).also { units.add(it) }
        }
            .takeWhile { it.y < maxY }
            .count()
    }

    fun totalSand(): Int {
        val units = rocks.toMutableSet()
        return generateSequence {
            units.fall(maxY + 1).also { units.add(it) }
        }
            .indexOf(Position(SOURCE_X, 0)) + 1
    }
}

private fun Set<Position>.fall(maxY: Int): Position {
    var x = SOURCE_X
    for (y in 1..maxY) {
        when {
            Position(x, y) !in this -> continue
            Position(x - 1, y) !in this -> x--
            Position(x + 1, y) !in this -> x++
            else -> return Position(x, y - 1)
        }
    }
    return Position(x, maxY)
}
