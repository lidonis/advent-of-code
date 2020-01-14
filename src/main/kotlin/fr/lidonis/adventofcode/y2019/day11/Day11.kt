package fr.lidonis.adventofcode.y2019.day11

import fr.lidonis.adventofcode.common.geo.plane.Position
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

    override fun part2() =
        robot(WHITE).run {
            compute()
            panels.keys.run {
                val xMin = minBy { it.x }?.x ?: 0
                val xMax = maxBy { it.x }?.x ?: 0
                val yMin = minBy { it.y }?.y ?: 0
                val yMax = maxBy { it.y }?.y ?: 0
                StringBuilder().run {
                    for (y in yMax downTo yMin) {
                        for (x in xMin..xMax) {
                            append(
                                when (panels.getValue(
                                    Position(
                                        x,
                                        y
                                    )
                                )) {
                                    BLACK -> ' '
                                    WHITE -> '█'
                                }
                            )
                        }
                        append('\n')
                    }
                    toString()
                }
            }
        }

    private fun robot(startingPanelColor: Color) = EmergencyHullPaintingRobot(computer, startingPanelColor)

    private val computer = IntCodeComputerFactory.buildIOComputer(input().text())
}
