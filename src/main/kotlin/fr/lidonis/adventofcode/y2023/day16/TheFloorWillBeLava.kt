package fr.lidonis.adventofcode.y2023.day16

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Direction.DOWN
import fr.lidonis.adventofcode.common.geo.plane.Direction.LEFT
import fr.lidonis.adventofcode.common.geo.plane.Direction.RIGHT
import fr.lidonis.adventofcode.common.geo.plane.Direction.UP
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

class TheFloorWillBeLava(lines: List<String>) {

    private val mirrors = lines.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            Mirror.from(c)?.let { Position(x, y) to it }
        }
    }.toMap()

    private val boundingBox = PositionSet(mirrors.keys).boundingBox

    fun part1() = countEnergized(RIGHT to Position(boundingBox.start.x - 1, 0))

    private fun countEnergized(initialBeam: Pair<Direction, Position>): Int {
        val energized = mutableSetOf<Pair<Direction, Position>>()
        var beams = mutableSetOf(initialBeam)
        while (beams.isNotEmpty()) {
            val newBeams = mutableSetOf<Pair<Direction, Position>>()
            for ((direction, position) in beams) {
                newBeams.addAll(bounce(position, direction, energized))
            }
            beams = newBeams
        }
        return energized.map { it.second }.toSet().size
    }

    private fun bounce(
        position: Position,
        direction: Direction,
        energized: MutableSet<Pair<Direction, Position>>
    ): Set<Pair<Direction, Position>> {
        val newPosition = position + direction
        return if (newPosition in boundingBox && energized.add(direction to newPosition)) {
            val mirror = mirrors[newPosition]
            mirror?.bounce(direction)?.map { it to newPosition }?.toSet()
                ?: setOf(direction to newPosition)
        } else {
            setOf()
        }
    }

    fun part2() = (up() + down() + right() + left()).maxOf { countEnergized(it) }

    private fun up() =
        (boundingBox.start.x..boundingBox.end.x)
            .map { UP to Position(it, boundingBox.start.y - 1) }

    private fun down() =
        (boundingBox.start.x..boundingBox.end.x)
            .map { DOWN to Position(it, boundingBox.end.y + 1) }

    private fun right() =
        (boundingBox.start.y..boundingBox.end.y)
            .map { RIGHT to Position(boundingBox.start.x - 1, it) }

    private fun left() =
        (boundingBox.start.y..boundingBox.end.y)
            .map { LEFT to Position(boundingBox.end.x + 1, it) }

    enum class Mirror(val value: Char) {
        VERTICAL('-') {
            override fun bounce(direction: Direction) =
                when (direction) {
                    UP, DOWN -> listOf(LEFT, RIGHT)
                    LEFT -> listOf(LEFT)
                    RIGHT -> listOf(RIGHT)
                }
        },
        HORIZONTAL('|') {
            override fun bounce(direction: Direction) =
                when (direction) {
                    LEFT, RIGHT -> listOf(UP, DOWN)
                    UP -> listOf(UP)
                    DOWN -> listOf(DOWN)
                }
        },
        RIGHT_UP('\\') {
            override fun bounce(direction: Direction) =
                when (direction) {
                    UP -> listOf(RIGHT)
                    RIGHT -> listOf(UP)
                    LEFT -> listOf(DOWN)
                    DOWN -> listOf(LEFT)
                }
        },
        RIGHT_DOWN('/') {
            override fun bounce(direction: Direction) =
                when (direction) {
                    UP -> listOf(LEFT)
                    RIGHT -> listOf(DOWN)
                    LEFT -> listOf(UP)
                    DOWN -> listOf(RIGHT)
                }
        };

        abstract fun bounce(direction: Direction): List<Direction>

        companion object {
            fun from(value: Char) = entries.firstOrNull { it.value == value }
        }
    }
}
