package fr.lidonis.adventofcode.y2022.day23

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

class UnstableDiffusion(
    val map: Set<Position>,
    private val direction: OrthogonalDirection = OrthogonalDirection.NORTH
) {

    fun evolve(nbRound: Int) = sequence().elementAt(nbRound)

    fun numberOfTheFirstRoundWhereNoElfMoves() =
        sequence()
            .zipWithNext()
            .indexOfFirst { it.first.map == it.second.map } + 1

    private fun evolve(): UnstableDiffusion {
        val proposedMoves = map.associateWith {
            it.proposeMove()
        }

        val allowedMove = proposedMoves.values.groupingBy { it }.eachCount().filter { it.value <= 1 }

        return UnstableDiffusion(proposedMoves.map {
            if (it.value in allowedMove) it.value else it.key
        }.toSet(), direction + 1)
    }

    private fun sequence() = generateSequence(this) { it.evolve() }

    fun countEmptyTile() = PositionSet(map).boundingBox.let {
        (it.end.x - it.start.x + 1) * (it.end.y - it.start.y + 1) - map.size
    }

    private fun Position.proposeMove(): Position {
        if (anyNeighbours()) {
            for (direction in direction.followingDirections()) {
                if (noNeighbours(direction)) return this + direction
            }
        }
        return this
    }

    private fun Position.anyNeighbours() =
        neighboursPositions().any { it in map }

    private fun Position.neighboursPositions() =
        neighbourPositions
            .asSequence()
            .map { this + it }

    private fun Position.noNeighbours(direction: OrthogonalDirection) =
        direction.adjacentDirections
            .asSequence()
            .map { this + it }
            .none { it in map }

    @OptIn(ExperimentalStdlibApi::class)
    fun display(row: Int, column: Int) = buildString {
        for (i in 0..<row) {
            for (j in 0..<column) {
                if (Position(j, i) in map) {
                    append('#')
                } else {
                    append('.')
                }
            }
            if (i < row - 1) appendLine()
        }
    }

    companion object {
        fun parse(input: String, direction: OrthogonalDirection = OrthogonalDirection.NORTH): UnstableDiffusion {
            return UnstableDiffusion(input.lines().flatMapIndexed { row, line ->
                line.mapIndexedNotNull { column, c ->
                    if (c == '#') Position(column, row) else null
                }
            }.toSet(), direction)
        }
    }
}

private operator fun Position.plus(direction: OrthogonalDirection) = this + direction.move


private val neighbourPositions = listOf(
    Position(-1, -1),
    Position(0, -1),
    Position(1, -1),
    Position(-1, 1),
    Position(0, 1),
    Position(1, 1),
    Position(-1, 0),
    Position(1, 0),
)

enum class OrthogonalDirection(val move: Position, val adjacentDirections: List<Position>) {
    NORTH(
        Position(0, -1),
        listOf(
            Position(-1, -1),
            Position(0, -1),
            Position(1, -1),
        )
    ),
    SOUTH(
        Position(0, 1),
        listOf(
            Position(-1, 1),
            Position(0, 1),
            Position(1, 1),
        )
    ),
    WEST(
        Position(-1, 0),
        listOf(
            Position(-1, -1),
            Position(-1, 0),
            Position(-1, 1),
        )
    ),
    EAST(
        Position(1, 0),
        listOf(
            Position(1, -1),
            Position(1, 0),
            Position(1, 1),
        )
    );

    operator fun plus(value: Int) = values()[Math.floorMod(ordinal + value, values().size)]

    @OptIn(ExperimentalStdlibApi::class)
    fun followingDirections() = (0..<values().size).map { this + it }
}
