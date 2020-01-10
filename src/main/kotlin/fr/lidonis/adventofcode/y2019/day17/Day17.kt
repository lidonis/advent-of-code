package fr.lidonis.adventofcode.y2019.day17

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The sum of the alignment parameters is ${Day17.part1()}")
    println("The vacuum robot has collected ${Day17.part2()} dust")
}

object Day17 : AdventOfCode2019(17) {

    override fun part1() = ascii.sumOfTheAlignmentParameters()

    override fun part2() = ascii.amountOfDustCollected() ?: error("No dust collected")

    private val ascii = AftScaffoldingControlAndInformationInterface(input().text())

}
