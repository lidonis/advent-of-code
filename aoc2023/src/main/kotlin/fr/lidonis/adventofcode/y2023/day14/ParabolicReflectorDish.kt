package fr.lidonis.adventofcode.y2023.day14

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.takeWhileInclusive

typealias RocksMap = Map<Position, ParabolicReflectorDish.Rock>

private const val NB_CYCLE = 1_000_000_000

class ParabolicReflectorDish(lines: List<String>) {

    private val rocks = lines.flatMapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            when (c) {
                '#' -> Position(x, y) to CubeRock
                'O' -> Position(x, y) to RoundRock
                else -> {
                    null
                }
            }
        }
    }.toMap()

    private val height = lines.size

    private fun RocksMap.moveNorth(): RocksMap {
        val newRocks = mutableMapOf<Position, Rock>()
        for ((position, rock) in this) {
            var currentPosition = position
            var newPosition: Position
            while (true) {
                newPosition = rock.moveNorth(currentPosition)
                if (newPosition.noMove(currentPosition) || newPosition.collision(newRocks)) {
                    newRocks[currentPosition] = rock
                    break
                }
                currentPosition = newPosition
            }
        }
        return newRocks
    }

    private fun Position.noMove(position: Position) = position == this

    private fun Position.collision(rockMap: RocksMap) = this in rockMap

    fun part1() = rocks.moveNorth().weight()

    private fun RocksMap.weight() =
        entries.sumOf { (position, rock) ->
            when (rock) {
                is RoundRock -> height - position.y
                is CubeRock -> 0
            }
        }

    fun part2(): Int {
        val existingMaps = mutableSetOf<RocksMap>()
        val movedRocks = rocks.sequence().takeWhileInclusive {
            existingMaps.add(it)
        }.toList()
        val cycleStart = movedRocks.last().let { existingMaps.indexOf(it) }
        val cycleSize = movedRocks.size - cycleStart - 1
        val element = cycleStart + (NB_CYCLE - cycleStart) % cycleSize

        return movedRocks[element].weight()
    }

    private fun Map<Position, Rock>.rotate() =
        map { (position, rock) ->
            position.copy(x = height - 1 - position.y, y = position.x) to rock
        }.sortedBy { it.first.y }.toMap()

    private fun Map<Position, Rock>.sequence() =
        generateSequence(this) {
            it
                .moveNorth()
                .rotate()
                .moveNorth()
                .rotate()
                .moveNorth()
                .rotate()
                .moveNorth()
                .rotate()
        }

    sealed interface Rock {
        fun moveNorth(position: Position): Position
    }

    data object CubeRock : Rock {
        override fun moveNorth(position: Position) = position
    }

    data object RoundRock : Rock {
        override fun moveNorth(position: Position) =
            position.copy(
                x = position.x,
                y = (position.y - 1).coerceAtLeast(0)
            )
    }
}
