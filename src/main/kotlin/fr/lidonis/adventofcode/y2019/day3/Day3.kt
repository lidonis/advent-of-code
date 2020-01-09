package fr.lidonis.adventofcode.y2019.day3

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("Manhattan distance from the central port to the closest intersection: ${Day3.part1()}")
    println("Fewest combined steps the wires must take to reach an intersection: ${Day3.part2()}")
}

object Day3 : AdventOfCode2019(3) {

    override fun part1() = wires.minimumDistance() ?: error("No intersection found")
    override fun part2() = wires.fewestSteps() ?: error("No intersection found")

    private val wires = input().lines().map { line -> line.split(",") }.run {
        CrossedWires(this[0], this[1])
    }

}

