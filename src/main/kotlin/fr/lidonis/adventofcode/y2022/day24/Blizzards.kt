package fr.lidonis.adventofcode.y2022.day24

import fr.lidonis.adventofcode.common.geo.plane.Position

private const val WALL = '#'
private const val EMPTY = '.'

class Blizzards(
    val positions: List<Pair<Position, Direction>>,
    val row: Int,
    val column: Int,
) {

    fun evolve(nbSteps: Int) = generateSequence(this) { it.evolve() }.elementAt(nbSteps)

    private fun evolve() = Blizzards(
        positions.map { it.evolve() },
        row,
        column
    )

    private fun Pair<Position, Direction>.evolve(): Pair<Position, Direction> {
        val position = first + second.move
        return (
            when {
                position.x == 0 -> Position(column - 2, position.y)
                position.x == column - 1 -> Position(1, position.y)
                position.y == 0 -> Position(position.x, row - 2)
                position.y == row - 1 -> Position(position.x, 1)
                else -> position
            }
            ) to second
    }

    fun display() = buildString {
        repeat(column) { if (it == 1) append(EMPTY) else append(WALL) }
        appendLine()
        repeat(row - 2) { j ->
            append(WALL)
            repeat(column - 2) { i ->
                val blizzards = positions.filter { it.first == Position(i + 1, j + 1) }
                when (val nbBlizzards = blizzards.count()) {
                    0 -> append(EMPTY)
                    1 -> append(blizzards.first().second.symbol)
                    else -> append(nbBlizzards)
                }
            }
            append(WALL)
            appendLine()
        }
        repeat(column) { if (it == column - 2) append(EMPTY) else append(WALL) }
    }

    companion object {
        fun parse(input: String): Blizzards {
            val lines = input.lines()
            return Blizzards(
                lines
                    .flatMapIndexed { row, line ->
                        line.flatMapIndexed { column, c ->
                            buildList {
                                val position = Position(column, row)
                                Direction.fromSymbol(c)
                                    ?.let { add(position to it) }
                                    ?: c.digitToIntOrNull()
                                        ?.let { repeat(it) { add(position to Direction.UP) } }
                            }
                        }
                    },
                lines.size,
                lines.first().length
            )
        }
    }
}
