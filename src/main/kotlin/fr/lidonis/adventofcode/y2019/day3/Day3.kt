package fr.lidonis.adventofcode.y2019.day3

import InputReader

fun main() {
    println("Manhattan distance from the central port to the closest intersection: ${Day3.part1()}")
    println("Fewest combined steps the wires must take to reach an intersection: ${Day3.part2()}")
}

object Day3 {

    fun part1(): Int? {
        val inputs = inputs()
        return CrossedWires(inputs[0], inputs[1]).minDistance()
    }

    fun part2(): Int? {
        val inputs = inputs()
        return CrossedWires(inputs[0], inputs[1]).fewestSteps()
    }

    private fun inputs() = InputReader("day3.txt").asLinesOfStrings()
}

