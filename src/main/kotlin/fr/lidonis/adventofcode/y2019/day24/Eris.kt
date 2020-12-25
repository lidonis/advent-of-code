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

    fun evolve() = evolutions().elementAt(1)

    fun evolutions() = generateSequence(bugs) { current ->
        val activated = current.fold(mutableMapOf<Position, Int>()) { acc, j ->
            neighbours(j).forEach { acc[it] = (acc[it] ?: 0) + 1 }
            acc
        }.filterValues { it in 1..2 }.keys - current
        val stayActive = current.filter {
            (neighbours(it) intersect current).size == 1
        }
        activated + stayActive
    }.map { Eris(it) }

    fun recursiveEvolve(aboveEris: Eris, belowEris: Eris) = Eris(runThrough { i, j ->
        val position = Position(i, j)
        position to when (position) {
            CENTER -> ERIS
            in bugs -> BUG
            else -> EMPTY
        }
    }.mapNotNull { tile ->
        if (let {
                val bugCount = it.countBugRec(tile.first.neighbours(), tile.first, aboveEris, belowEris).sum()
                when {
                    tile.second == ERIS -> ERIS
                    tile.second == BUG && bugCount == 1 -> BUG
                    tile.second == EMPTY && bugCount in 1..2 -> BUG
                    else -> EMPTY
                }
            } == BUG
        ) tile.first else null
    }.toSet())

    private fun neighbours(center: Position) = center.neighbours().filter { position ->
        position.x in 0 until SIZE && position.y in 0 until SIZE
    }

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

    override fun toString() = '\n' + (0 until SIZE).flatMap { i ->
        (0 until SIZE).map { j ->
            if(bugs.contains(Position(j,i))) '#' else '.'
        } + '\n'
    }.joinToString("")

    enum class State(val c: Char) {
        BUG('#'), EMPTY('.'), ERIS('?');

        companion object {
            fun valueOf(c: Char) = values().find { it.c == c }
        }
    }
}
