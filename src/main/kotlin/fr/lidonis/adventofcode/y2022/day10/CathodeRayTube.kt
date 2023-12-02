package fr.lidonis.adventofcode.y2022.day10

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.ocr.OCR

private const val INSTRUCTION_ADD_X = "addx "

private const val FIRST_CYCLE = 20
private const val LAST_CYCLE = 220
private const val CYCLE_STEP = 40

private const val CRT_WIDTH = 40
private const val CRT_HEIGHT = 6

class CathodeRayTube(private val lines: List<String>) {

    internal fun cycle() = sequence {
        var x = 1
        yield(x)
        for (instruction in lines) {
            yield(x)
            if (instruction.startsWith(INSTRUCTION_ADD_X)) {
                x += instruction.substringAfter(INSTRUCTION_ADD_X).toInt()
                yield(x)
            }
        }
    }

    fun signalStrengths(): List<Int> {
        val cycle = cycle().take(LAST_CYCLE).toList()
        return (FIRST_CYCLE..LAST_CYCLE step CYCLE_STEP).map { it * cycle[it - 1] }
    }

    internal fun print() = positionSet().buildString()

    fun ocr() = OCR.detect(positionSet())

    private fun positionSet(): PositionSet {
        val cycle = cycle().iterator()
        val positionSet = PositionSet(
            buildSet {
                repeat(CRT_HEIGHT) { height ->
                    repeat(CRT_WIDTH) { c ->
                        val sprite = cycle.next()
                        if (sprite - c in -1..1) add(Position(c, height))
                    }
                }
            }
        )
        return positionSet
    }
}
