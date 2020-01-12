package fr.lidonis.adventofcode.y2019.day24

import Direction
import Position
import fr.lidonis.adventofcode.common.pow
import fr.lidonis.adventofcode.y2019.day24.Eris.State.*

open class Eris(private val scan: List<Tile>) {
    constructor(input: String) : this(input.lines().mapIndexed { i, line ->
        line.mapIndexed { j, c ->
            Tile(
                Position(j, i),
                State.valueOf(c)
                    ?: error { "Unknown tile" })
        }
    }.flatten())

    val biodiversityRating by lazy {
        scan.mapIndexedNotNull { i, tile ->
            if (tile.state == BUG) 2.pow(i) else null
        }.sum()
    }

    fun evolve() = Eris(scan.map { tile ->
        tile to tile.position.neighbours().mapNotNull { position ->
            scan.find { it.position == position }
        }.count { it.state == BUG }
    }.map {
        evolve(it)
    })

    private fun evolve(pair: Pair<Tile, Int>): Tile {
        if (pair.first.state == ERIS) return pair.first
        val bug = if (pair.first.state == BUG) {
            pair.second == 1
        } else {
            pair.second == 1 || pair.second == 2
        }
        return Tile(
            pair.first.position,
            if (bug) BUG else EMPTY
        )
    }

    fun recursiveEvolve(aboveEris: Eris, belowEris: Eris) =
        Eris(scan.map { tile ->
            tile to tile.position.neighbours().map { position ->
                var neighbourBugsCount = 0
                if (position.x < 0) neighbourBugsCount += aboveEris.count(Position(1, 2))
                if (position.x > 4) neighbourBugsCount += aboveEris.count(Position(3, 2))
                if (position.y < 0) neighbourBugsCount += aboveEris.count(Position(2, 1))
                if (position.y > 4) neighbourBugsCount += aboveEris.count(Position(2, 3))
                if (position.x == 2 && position.y == 2) {
                    when (tile.position) {
                        Position(2, 1) -> neighbourBugsCount += belowEris.count(Direction.UP)
                        Position(3, 2) -> neighbourBugsCount += belowEris.count(Direction.RIGHT)
                        Position(2, 3) -> neighbourBugsCount += belowEris.count(Direction.DOWN)
                        Position(1, 2) -> neighbourBugsCount += belowEris.count(Direction.LEFT)
                    }
                }
                if (scan.find { it.position == position }?.state == BUG) neighbourBugsCount++
                neighbourBugsCount
            }.sum()
        }.map {
            evolve(it)
        })

    fun count() = scan.count { it.state == BUG }

    private fun count(direction: Direction) = when (direction) {
        Direction.UP -> scan.filter { it.position.y == 0 }
        Direction.RIGHT -> scan.filter { it.position.x == 4 }
        Direction.DOWN -> scan.filter { it.position.y == 4 }
        Direction.LEFT -> scan.filter { it.position.x == 0 }
    }.count { it.state == BUG }

    private fun count(position: Position) = if (scan.find { it.position == position }?.state == BUG) 1 else 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Eris) return false

        if (biodiversityRating != other.biodiversityRating) return false

        return true
    }

    override fun hashCode(): Int {
        return biodiversityRating.hashCode()
    }

    override fun toString() = '\n' + (0 until 5).flatMap { i ->
        (0 until 5).map { j ->
            scan.find { it.position == Position(j, i) }?.state?.c
        } + '\n'
    }.joinToString("")

    object EmptyRecursiveEris : Eris((0 until 5).flatMap { i ->
        (0 until 5).map { j ->
            if (i == 2 && j == 2) {
                Tile(Position(i, j), ERIS)
            } else {
                Tile(Position(j, i), EMPTY)
            }
        }
    })

    fun toRecursive() = Eris(
        scan - Tile(
            Position(2, 2),
            EMPTY
        ) + Tile(Position(2, 2), ERIS)
    )

    data class Tile(val position: Position, val state: State = EMPTY)
    enum class State(val c: Char) {
        BUG('#'), EMPTY('.'), ERIS('?');

        companion object {
            fun valueOf(c: Char) = values().find { it.c == c }
        }
    }
}

data class RecursiveEris(val erises: List<Eris>) {
    fun evolve(minutes: Int): RecursiveEris {
        var rerises = this
        repeat(minutes) { rerises = rerises.evolve() }
        return rerises
    }

    private fun evolve(): RecursiveEris {
        val evolved =
            (listOf(Eris.EmptyRecursiveEris.recursiveEvolve(Eris.EmptyRecursiveEris, erises.first())) +
                    (listOf(Eris.EmptyRecursiveEris) + erises + Eris.EmptyRecursiveEris).windowed(
                        3,
                        partialWindows = true
                    ).map {
                        when (it.size) {
                            3 -> it[1].recursiveEvolve(it[0], it[2])
                            2 -> it[1].recursiveEvolve(it[0], Eris.EmptyRecursiveEris)
                            else -> Eris.EmptyRecursiveEris
                        }
                    }).toMutableList()
        if (evolved.first() == Eris.EmptyRecursiveEris) {
            evolved.removeAt(0)
        }
        if (evolved.first() == Eris.EmptyRecursiveEris) {
            evolved.removeAt(0)
        }
        if (evolved.last() == Eris.EmptyRecursiveEris) {
            evolved.removeAt(evolved.size - 1)
        }
        if (evolved.last() == Eris.EmptyRecursiveEris) {
            evolved.removeAt(evolved.size - 1)
        }
        return RecursiveEris(evolved)
    }

    fun count() = erises.map(Eris::count).sum()
}