package fr.lidonis.adventofcode.y2021.day15

import fr.lidonis.adventofcode.common.geo.plane.Position

class Chiton(lines: List<String>) {

    val risks = lines.flatMapIndexed { j, line ->
        line.mapIndexed { i, num ->
            Position(i, j) to num.digitToInt()
        }
    }.toMap()


    fun lowestRiskPath(): Int {
        return 1
    }
}