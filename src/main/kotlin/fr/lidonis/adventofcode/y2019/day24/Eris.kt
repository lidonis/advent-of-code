package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Direction.DOWN
import fr.lidonis.adventofcode.common.geo.plane.Direction.LEFT
import fr.lidonis.adventofcode.common.geo.plane.Direction.RIGHT
import fr.lidonis.adventofcode.common.geo.plane.Direction.UP
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.math.pow
import fr.lidonis.adventofcode.y2019.day24.Eris.State.BUG
import fr.lidonis.adventofcode.y2019.day24.Eris.State.EMPTY
import fr.lidonis.adventofcode.y2019.day24.Eris.State.ERIS

private const val SIZE = 5
private const val WINDOW_SIZE = 3
private val CENTER = Position(SIZE / 2, SIZE / 2)

class Eris(private val bugs: Set<Position>) {

    constructor(input: String) : this(sequence {
        for ((i, line) in input.lines().withIndex()) {
            for ((j, c) in line.withIndex()) {
                if (State.valueOf(c) == BUG) yield(Position(j, i))
            }
        }
    }.toSet())

    val biodiversityRating by lazy {
        bugs.sumBy { 2 pow (it.x + it.y * SIZE) }
    }

    fun evolve() = Eris(runThrough { i, j ->
        val position = Position(i, j)
        position to if (position in bugs) BUG else EMPTY
    }.mapNotNull { tile ->
        if (evolve(tile.second, countBugs(tile.first.neighbours())) == BUG) tile.first else null
    }.toSet())

    private fun countBugs(positions: List<Position>) = positions.count { it in bugs }

    private fun evolve(currentState: State, bugCount: Int) = when {
        currentState == ERIS -> ERIS
        currentState == BUG && bugCount == 1 -> BUG
        currentState == EMPTY && bugCount in 1..2 -> BUG
        else -> EMPTY
    }

    fun recursiveEvolve(aboveEris: Eris, belowEris: Eris) = Eris(runThrough { i, j ->
        val position = Position(i, j)
        position to when (position) {
            CENTER -> ERIS
            in bugs -> BUG
            else -> EMPTY
        }
    }.mapNotNull { tile ->
        if (evolve(
                tile.second,
                countBugRec(tile.first.neighbours(), tile.first, aboveEris, belowEris).sum()
            ) == BUG
        ) tile.first else null
    }.toSet())

    private fun countBugRec(positions: List<Position>, current: Position, aboveEris: Eris, belowEris: Eris) =
        positions.map { position ->
            when {
                position.x == -1 -> aboveEris.count(CENTER + LEFT)
                position.x == SIZE -> aboveEris.count(CENTER + RIGHT)
                position.y == -1 -> aboveEris.count(CENTER + DOWN)
                position.y == SIZE -> aboveEris.count(CENTER + UP)
                position == CENTER -> Direction.fromPosition(current - CENTER)?.let { belowEris.count(it) } ?: 0
                else -> count(position)
            }
        }

    fun countBugs() = bugs.size

    private fun <T> runThrough(function: (Int, Int) -> T) = (0 until SIZE).flatMap { i ->
        (0 until SIZE).map { j ->
            function(j, i)
        }
    }

    private fun count(direction: Direction) = bugs.count {
        when (direction) {
            DOWN -> it.y == 0
            RIGHT -> it.x == SIZE - 1
            UP -> it.y == SIZE - 1
            LEFT -> it.x == 0
        }
    }

    private fun count(position: Position) = if (position in bugs) 1 else 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Eris) return false
        if (biodiversityRating == other.biodiversityRating) return true
        return false
    }

    override fun hashCode() = biodiversityRating.hashCode()

    enum class State(val c: Char) {
        BUG('#'), EMPTY('.'), ERIS('?');

        companion object {
            fun valueOf(c: Char) = values().find { it.c == c }
        }
    }
}

data class RecursiveEris(val erises: List<Eris>) {

    fun evolve(minutes: Int) = (1..minutes).fold(this) { reris, _ -> reris.evolve() }

    private fun evolve(): RecursiveEris {
        val evolved =
            (listOf(Eris(emptySet()).recursiveEvolve(Eris(emptySet()), erises.first())) +
                    (listOf(Eris(emptySet())) + erises + Eris(emptySet())).windowed(
                        WINDOW_SIZE,
                        partialWindows = true
                    ).map {
                        when (it.size) {
                            WINDOW_SIZE -> it[1].recursiveEvolve(it[0], it[2])
                            WINDOW_SIZE - 1 -> it[1].recursiveEvolve(it[0], Eris(emptySet()))
                            else -> Eris(emptySet())
                        }
                    }).toMutableList()
        if (evolved.first() == Eris(emptySet())) {
            evolved.removeAt(0)
        }
        if (evolved.first() == Eris(emptySet())) {
            evolved.removeAt(0)
        }
        if (evolved.last() == Eris(emptySet())) {
            evolved.removeAt(evolved.size - 1)
        }
        if (evolved.last() == Eris(emptySet())) {
            evolved.removeAt(evolved.size - 1)
        }
        return RecursiveEris(evolved)
    }

    fun countBugs() = erises.map(Eris::countBugs).sum()
}
