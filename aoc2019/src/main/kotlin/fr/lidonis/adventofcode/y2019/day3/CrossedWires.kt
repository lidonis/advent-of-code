package fr.lidonis.adventofcode.y2019.day3

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.tail

class CrossedWires(input1: List<String>, input2: List<String>) {
    private val startingPosition = Position(1, 1)

    private var wire1 = Wire(startingPosition, input1)
    private var wire2 = Wire(startingPosition, input2)

    constructor(input1: String, input2: String) : this(input1.split(","), input2.split(","))

    fun minimumDistance() =
        (wire1.tail intersect wire2.tail.toSet())
            .minOfOrNull { startingPosition.manhattanDistance(it) }

    fun fewestSteps() =
        (wire1.tail intersect wire2.tail.toSet())
            .minOfOrNull { wire1.indexOf(it) + wire2.indexOf(it) }

    class Wire(private val positions: List<Position>) : List<Position> by positions {
        constructor(
            startingPosition: Position,
            moves: List<String>
        ) : this(
            moves.fold(listOf(startingPosition)) { acc, move ->
                acc + Move.read(move).moves(acc.last())
            }
        )
    }

    data class Move(val direction: Direction, val size: Int) {

        companion object MoveInterpreter {
            fun read(input: String) = Move(
                Direction.fromChar(input[0]) ?: error("Invalid move"),
                input.substring(1).toInt()
            )
        }

        fun moves(startingPosition: Position) =
            (0 until size).fold(listOf(startingPosition)) { acc, _ -> acc + (acc.last() + direction) }.drop(1)
    }
}
