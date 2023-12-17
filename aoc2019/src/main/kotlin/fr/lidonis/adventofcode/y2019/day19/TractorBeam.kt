package fr.lidonis.adventofcode.y2019.day19

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val MULTIPLIER = 10000
private const val MAX_SEARCH = 2000
private const val MIN_SEARCH = 1800

class TractorBeam(private val program: String) {

    fun countAffected(size: Int) = BeamMap(program).affected(0, size).count()

    fun fit(size: Int) = BeamMap(program).let { beamMap ->
        beamMap.affected(MIN_SEARCH, MAX_SEARCH).first { beamMap.rightSize(it, size) }
        .let { it.x * MULTIPLIER + it.y }
    }

    class BeamMap(program: String) {

        private val computer =
            IntCodeComputerFactory.buildIOComputer(
                program
            )
        private val map = mutableMapOf<Position, Long>()

        operator fun get(position: Position) =
            map.computeIfAbsent(position) {
                computer.run {
                    reset()
                    input(it.x.toLong())
                    input(it.y.toLong())
                    nextOutput() ?: error { "Position not found $it" }
                }
            }

        fun affected(min: Int, max: Int): MutableList<Position> {
            val affected = mutableListOf<Position>()
            for (i in min until max) {
                for (j in i until max) {
                    val position = Position(i, j)
                    val output = this[position]
                    if (output == 1L) {
                        affected.add(position)
                    }
                }
            }
            return affected
        }

        fun rightSize(position: Position, size: Int) =
            this[Position(position.x + size - 1, position.y)] == 1L &&
                    this[
                        Position(
                            position.x,
                            position.y + size - 1
                        )
                    ] == 1L
    }
}
