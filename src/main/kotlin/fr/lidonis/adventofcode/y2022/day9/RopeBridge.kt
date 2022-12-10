package fr.lidonis.adventofcode.y2022.day9

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import kotlin.math.absoluteValue
import kotlin.math.sign

class RopeBridge(private val lines: List<String>) {

    private val headPositions = sequence {
        var position = Position.ORIGIN
        yield(position)
        for (line in lines) {
            val direction = Direction.fromChar(line[0]) ?: error("unknown direction")
            repeat(line.drop(2).toInt()) {
                position += direction
                yield(position)
            }
        }
    }

    fun move(nbTails: Int): Set<Position> {
        var moves = headPositions
        repeat(nbTails) {
            moves = follow(moves)
        }
        return moves.toSet()
    }

    private fun follow(movesToFollow: Sequence<Position>): Sequence<Position> = sequence {
        var position = Position.ORIGIN
        yield(position)
        for (follow in movesToFollow) {
            val delta = follow - position
            val isAdjacent = delta.x.absoluteValue <= 1 && delta.y.absoluteValue <= 1
            if (!isAdjacent) {
                position += Position(delta.x.sign, delta.y.sign)
            }
            yield(position)
        }
    }
}
