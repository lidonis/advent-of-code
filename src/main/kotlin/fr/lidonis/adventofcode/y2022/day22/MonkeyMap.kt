package fr.lidonis.adventofcode.y2022.day22

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position

private const val PATH_REGEX = """\d+|[LR]"""

class MonkeyMap(lines: List<String>) {

    private val map = lines.dropLast(2).flatMapIndexed { row, line ->
        line.mapIndexedNotNull { column, c ->
            when (c) {
                '.' -> Tile.OPEN
                '#' -> Tile.WALL
                else -> null
            }?.let { Position(row + 1, column + 1) to it }
        }
    }.toMap()
    private val path = lines.last()
        .let { PATH_REGEX.toRegex().findAll(it) }
        .map {
            when (it.value) {
                "R" -> Rotate.CLOCKWISE
                "L" -> Rotate.COUNTERCLOCKWISE
                else -> Move(it.value.toInt())
            }
        }.toList()

    fun password(): Any {
        var position = map.keys.first()
        var direction = Direction.RIGHT

        for (instruction in path) {
            when (instruction) {
                Rotate.CLOCKWISE -> direction++
                Rotate.COUNTERCLOCKWISE -> direction--
                is Move -> repeat(instruction.number) {
                    position += direction
                }
            }
            println("$position $direction")
        }

        return 1
    }

    fun part2() = 1


}

private enum class Tile {
    OPEN, WALL
}

private sealed interface Instruction

private data class Move(val number: Int) : Instruction

private enum class Rotate : Instruction {
    CLOCKWISE, COUNTERCLOCKWISE
}


