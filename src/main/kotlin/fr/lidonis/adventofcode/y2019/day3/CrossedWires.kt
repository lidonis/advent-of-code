package fr.lidonis.adventofcode.y2019.day3

import Direction
import Position

class CrossedWires(input1: List<String>, input2: List<String>) {
    private val startingPosition = Position(1, 1)

    private var wire1 = Wire(startingPosition, input1)
    private var wire2 = Wire(startingPosition, input2)

    constructor(input1: String, input2: String) : this(input1.split(","), input2.split(","))

    fun minDistance() = wire1.drop(1).intersect(wire2.drop(1)).map { startingPosition.manhattanDistance(it) }.min()

    fun fewestSteps(): Int? {
        return wire1.drop(1).intersect(wire2.drop(1)).map {
            wire1.indexOf(it) + wire2.indexOf(it)
        }.min()
    }

    class Wire(private val positions: List<Position>) : List<Position> by positions {

        constructor(
            startingPosition: Position,
            moves: List<String>
        ) : this(moves.fold(listOf(startingPosition)) { acc, move ->
            acc + Move.read(move).moves(acc.last())
        })

    }

    data class Move(val direction: Direction, val size: Int) {

        companion object MoveInterpreter {
            fun read(input: String): Move {
                return Move(
                    Direction.valueOf(input[0].toString()),
                    input.substring(1).toInt()
                )
            }
        }

        fun moves(startingPosition: Position) =
            (0 until size).fold(listOf(startingPosition), { acc, _ -> acc + acc.last().move(direction) }).drop(1)
    }
}