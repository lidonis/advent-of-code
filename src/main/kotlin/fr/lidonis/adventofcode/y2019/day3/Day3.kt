package fr.lidonis.adventofcode.y2019.day3

import InputReader

fun main() {
    println("Manhattan distance from the central port to the closest intersection: ${Day3.part1()}")
    println("Fewest combined steps the wires must take to reach an intersection: ${Day3.part2()}")
}

object Day3 {

    fun part1() = wires(inputs()).minDistance()

    fun part2() = wires(inputs()).fewestSteps()

    private fun wires(inputs: List<List<String>>) = CrossedWires(inputs[0], inputs[1])

    private fun inputs() = InputReader("day3.txt").asLinesOfStrings()
}

