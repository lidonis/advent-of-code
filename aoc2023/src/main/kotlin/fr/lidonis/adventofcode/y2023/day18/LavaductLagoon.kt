package fr.lidonis.adventofcode.y2023.day18

import fr.lidonis.adventofcode.common.geo.plane.DirectionUpNegative
import fr.lidonis.adventofcode.common.geo.plane.PositionLong
import fr.lidonis.adventofcode.common.geo.plane.perimeter
import fr.lidonis.adventofcode.common.geo.plane.shoelaceFormula

private val INSTRUCTION_REGEX = """([RDLU]) (\d+) \(#([0-9a-fA-F]{6})\)""".toRegex()

class LavaductLagoon(lines: List<String>) {

    private val digPlan =
        lines.map { line ->
            val (d, l, color) = INSTRUCTION_REGEX.matchEntire(line)?.destructured ?: error("Invalid instruction $line")
            Instruction.fromRegularInstruction(d, l) to Instruction.fromColorInstruction(color)
        }

    private val points by lazy {
        buildSet {
            var current = PositionLong.ORIGIN
            for ((instruction) in digPlan) {
                current += instruction.direction.move * instruction.distance
                add(current)
            }
        }
    }

    fun part1() = points.lagoonSize()

    private fun Set<PositionLong>.lagoonSize(): Long {
        val shoelace = shoelaceFormula().toLong()
        val perimeter = perimeter()
        return shoelace + perimeter + 1
    }

    private val pointsFromColor by lazy {
        buildSet {
            var current = PositionLong.ORIGIN
            for ((_, instruction) in digPlan) {
                current += instruction.direction.move * instruction.distance
                add(current)
            }
        }
    }

    fun part2() = pointsFromColor.lagoonSize()

    private data class Instruction(val direction: DirectionUpNegative, val distance: Int) {
        companion object {
            fun fromRegularInstruction(d: String, l: String) = Instruction(
                direction = DirectionUpNegative.fromLetter(d.first())
                    ?: error("Invalid direction $d"),
                distance = l.toInt(),
            )

            @OptIn(ExperimentalStdlibApi::class)
            fun fromColorInstruction(color: String) = Instruction(
                direction = DirectionUpNegative.fromInt(color.last().digitToInt())
                    ?: error("Invalid direction ${color.last()}"),
                distance = color.dropLast(1).hexToInt(),
            )
        }
    }
}
