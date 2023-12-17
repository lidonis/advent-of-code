package fr.lidonis.adventofcode.y2023.day10

import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative
import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative.DOWN
import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative.LEFT
import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative.RIGHT
import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative.UP
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

class PipeMaze(lines: List<String>) {

    private val map =
        lines.flatMapIndexed { j, s ->
            s.mapIndexedNotNull { i, c ->
                Pipe.fromChar(c)?.let { Position(i, j) to it }
            }
        }.toMap()

    fun part1() = loopPositions.size / 2

    private val startLocation = map.filterValues { it == Pipe.START }.keys.first()
    private val startNeighbours = findConnectedNeighbours(startLocation)
    private val loopPositions = run {
        var neighbours = startNeighbours
        val visited = mutableSetOf<Position>()
        while (visited.addAll(neighbours)) {
            neighbours = neighbours.flatMap { getNeighbours(it) }.toSet().subtract(visited)
        }
        visited
    }

    private fun findConnectedNeighbours(startLocation: Position) =
        DirectionUpNegative.entries.map { startLocation + it }.filter {
            getNeighbours(
                it
            ).contains(startLocation)
        }.toSet()

    private fun getNeighbours(position: Position) =
        map[position]?.connections?.map { position + it.move } ?: emptyList()

    fun part2(): Int {
        var enclosed = 0
        val blockingRayLoopPositions = findBlockingRayLoopPositions()
        val boundingBox = PositionSet(loopPositions).boundingBox
        for (j in (boundingBox.start.y)..boundingBox.end.y) {
            for (i in (boundingBox.start.x)..boundingBox.end.x) {
                enclosed += countEnclosedRegions(Position(i, j), blockingRayLoopPositions, boundingBox.end.x)
            }
        }
        return enclosed
    }

    private fun countEnclosedRegions(
        position: Position,
        blockingRayLoopPositions: List<Position>,
        mapSize: Int
    ): Int {
        if (position !in loopPositions) {
            var found = 0
            for (k in position.x..mapSize) {
                if (Position(k, position.y) in blockingRayLoopPositions) {
                    found++
                }
            }
            if (found % 2 == 1) {
                return 1
            }
        }
        return 0
    }

    private fun findBlockingRayLoopPositions(): List<Position> {
        val blockingPipes = findBlockingPipes()
        val blockingRayLoopPositions = loopPositions.filter {
            map[it] in blockingPipes
        }
        return blockingRayLoopPositions
    }

    private fun findBlockingPipes() =
        Pipe.entries.filter {
            when (it) {
                Pipe.START -> getStartPipe().connections.contains(DOWN)
                else -> it.connections.contains(DOWN)
            }
        }

    private fun getStartPipe() =
        Pipe.entries.first { pipe ->
            pipe.connections
                .map { startLocation + it.move }
                .containsAll(startNeighbours)
        }

    enum class Pipe(val char: Char, val connections: List<DirectionUpNegative>) {
        HORIZONTAL('-', listOf(LEFT, RIGHT)),
        VERTICAL('|', listOf(UP, DOWN)),
        CORNER_NORTH_WEST('J', listOf(UP, LEFT)),
        CORNER_NORTH_EAST('L', listOf(UP, RIGHT)),
        CORNER_SOUTH_EAST('F', listOf(DOWN, RIGHT)),
        CORNER_SOUTH_WEST('7', listOf(DOWN, LEFT)),
        START('S', emptyList());

        companion object {
            fun fromChar(char: Char) = entries.firstOrNull { it.char == char }
        }
    }
}
