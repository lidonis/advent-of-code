package fr.lidonis.adventofcode.y2019.day11

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color.BLACK
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color.WHITE
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

fun main() {
    println("${Day11.part1()} panels painted at least once")
    println("The registration identifier is: \n\n${Day11.part2()}")
}

private const val DAY = 11

object Day11 : AdventOfCode2019(DAY) {

    override fun part1() = robot(BLACK).run {
        compute()
        panelsPaintedAtLeastOnce
    }

    private const val LETTER_WIDTH = 4

    override fun part2() =
        robot(WHITE).run {
            compute()
            panels
        }.run {
            val positionSets = PositionSet(this.filterValues { it == WHITE }.keys)
                .mirrorY()
                .chunked(LETTER_WIDTH + 1)

            StringBuilder().apply {
                for (positionSet in positionSets) {
                    append(stringBuilder(positionSet.moveTo(Position.ORIGIN))).append("\n")
                }
            }
        }

    private fun stringBuilder(positions: PositionSet): StringBuilder {
        val boundingBox = positions.boundingBox
        return StringBuilder().apply {
            for (y in boundingBox.start.y..boundingBox.end.y) {
                for (x in boundingBox.start.x..boundingBox.end.x) {
                    append(if (positions.contains(Position(x, y))) '█' else ' ')
                }
                append('\n')
            }
        }
    }

    private fun robot(startingPanelColor: Color) = EmergencyHullPaintingRobot(computer, startingPanelColor)

    private val computer = IntCodeComputerFactory.buildIOComputer(input().text())
}
