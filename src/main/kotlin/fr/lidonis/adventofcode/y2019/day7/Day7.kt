package fr.lidonis.adventofcode.y2019.day7

import fr.lidonis.adventofcode.common.permute
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The largest output signal that can be sent to the thrusters is ${Day7.part1()}")
    println("The highest signal that can be sent to the thrusters is ${Day7.part2()}")
}

private const val DAY = 7

object Day7 : AdventOfCode2019(DAY) {

    override fun part1() = largestSignal(0L..4) ?: error("No signal ...")
    override fun part2() = largestSignal(5L..9) ?: error("No signal ...")

    private fun largestSignal(range: LongRange) = range.toList().permute().map {
        Amplifiers(program, it).run()
    }.max()

    private val program = input().text()
}
