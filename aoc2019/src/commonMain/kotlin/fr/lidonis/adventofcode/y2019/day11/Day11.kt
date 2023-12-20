package fr.lidonis.adventofcode.y2019.day11

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.ocr.OCR
import fr.lidonis.adventofcode.y2019.AdventOfCode2019
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color.BLACK
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color.WHITE
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val DAY = 11

@Suppress("unused")
object Day11 : AdventOfCode2019(DAY) {

    private fun robot(startingPanelColor: Color) =
        EmergencyHullPaintingRobot(IntCodeComputerFactory.buildIOComputer(input()), startingPanelColor)

    @Answer("1876")
    override fun part1() = robot(BLACK).run {
        compute()
        panelsPaintedAtLeastOnce
    }

    @Answer("CGPJCGCL")
    override fun part2() =
        robot(WHITE).run {
            compute()
            panels
        }.run {
            OCR.detect(PositionSet(this.filterValues { it == WHITE }.keys).mirrorY())
        }
}
