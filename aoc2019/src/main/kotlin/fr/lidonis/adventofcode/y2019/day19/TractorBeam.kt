package fr.lidonis.adventofcode.y2019.day19

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val MULTIPLIER = 10000
private const val MAX_SEARCH = 2000
private const val MIN_SEARCH = 1800

class TractorBeam(program: String) {
    private val beamMap = BeamMap(program)

    fun countAffected(size: Int): Int {
        return affected(0, size).count()
    }

    private fun affected(min: Int, max: Int): MutableList<Position> {
        val affected = mutableListOf<Position>()
        for (i in min until max) {
            for (j in i until max) {
                val position = Position(i, j)
                val output = beamMap[position]
                if (output == 1L) {
                    affected.add(position)
                }
            }
        }
        return affected
    }

    fun fit(size: Int) = affected(MIN_SEARCH, MAX_SEARCH).first { rightSize(it, size) }
        .let { it.x * MULTIPLIER + it.y }

    private fun rightSize(position: Position, size: Int) =
        beamMap[Position(position.x + size - 1, position.y)] == 1L &&
            beamMap[
                Position(
                    position.x,
                    position.y + size - 1
                )
            ] == 1L

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
    }
}
