package fr.lidonis.adventofcode.y2020.day11

import fr.lidonis.adventofcode.common.geo.plane.Position

abstract class SeatingSystem(private val layout: MutableMap<Position, State>) {

    private var changed = false
    private var occupiedSeatCount = 0
    abstract val overcrowdedCount: Int

    fun evolve() = evolve { evolutionFunction(this::neighboursState, overcrowdedCount) }

    abstract fun neighboursState(current: Position): List<State>

    private fun evolve(function: () -> Unit): SeatingSystem {
        changed = false
        function()
        return this
    }

    private fun evolutionFunction(neighboursStateFunction: (Position) -> List<State>, overcrowdedCount: Int) {
        layout.map { entry ->
            entry to (neighboursStateFunction(entry.key).count { it == State.OCCUPIED_SEAT })
        }.forEach { (entry, occupiedSeatCount) ->
            when {
                entry.value == State.EMPTY_SEAT && occupiedSeatCount == 0 -> seat(entry.key)
                entry.value == State.OCCUPIED_SEAT && occupiedSeatCount >= overcrowdedCount -> leave(entry.key)
            }
        }
    }

    private fun leave(position: Position) {
        layout[position] = State.EMPTY_SEAT
        changed = true
        occupiedSeatCount--
    }

    private fun seat(position: Position) {
        layout[position] = State.OCCUPIED_SEAT
        changed = true
        occupiedSeatCount++
    }

    fun stabilize(): Int = evolve().let { evolve ->
        if (!this.changed) occupiedSeatCount else evolve.stabilize()
    }

    enum class State(val c: Char) {
        OCCUPIED_SEAT('#'), FLOOR('.'), EMPTY_SEAT('L');

        companion object {
            fun valueOf(c: Char) = values().find { it.c == c }
        }
    }

    companion object {
        fun toMutableMap(input: List<String>) = sequence {
            for ((i, line) in input.withIndex()) {
                for ((j, c) in line.withIndex()) {
                    yield(Position(j, i) to (State.valueOf(c) ?: error("Unknown state")))
                }
            }
        }.toMap().toMutableMap()

        val ALL_DIRECTIONS = listOf(
            Position(0, 1),
            Position(1, 0),
            Position(0, -1),
            Position(-1, 0),
            Position(-1, -1),
            Position(-1, 1),
            Position(1, 1),
            Position(1, -1)
        )
    }
}

private const val ADJACENT_OVERCROWDED_COUNT = 4

data class AdjacentSeatingSystem(private val layout: MutableMap<Position, State>) : SeatingSystem(layout) {

    override val overcrowdedCount = ADJACENT_OVERCROWDED_COUNT
    private val adjacentNeighbours = mutableMapOf<Position, List<Position>>()

    constructor(input: List<String>) : this(toMutableMap(input))

    override fun neighboursState(current: Position) =
        adjacentNeighbours.getOrPut(current, {
            ALL_DIRECTIONS.map { it + current }
        }).mapNotNull { layout[it] }
}

private const val FIRST_OVERCROWDED_COUNT = 5

data class FirstSeatingSystem(private val layout: MutableMap<Position, State>) : SeatingSystem(layout) {

    override val overcrowdedCount = FIRST_OVERCROWDED_COUNT
    private val firstNeighbours = mutableMapOf<Position, List<Position>>()

    constructor(input: List<String>) : this(toMutableMap(input))

    override fun neighboursState(current: Position): List<State> =
        firstNeighbours.getOrPut(current, {
            ALL_DIRECTIONS.map { look(current, it) }
        }).mapNotNull { layout[it] }

    private fun look(
        current: Position,
        direction: Position
    ): Position = when (layout[current + direction]) {
        State.FLOOR -> look(current + direction, direction)
        else -> current + direction
    }

}
