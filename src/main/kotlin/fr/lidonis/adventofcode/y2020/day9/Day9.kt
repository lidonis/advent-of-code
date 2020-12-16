package fr.lidonis.adventofcode.y2020.day9

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 9

object Day9 : AdventOfCode2020(DAY) {

    override fun part1() = additionSystem.findInvalidNumber() ?: error("No invalid number found")

    override fun part2() = additionSystem.findWeakness() ?: error("No weakness found")

    private const val PREAMBLE_SIZE = 25
    private val additionSystem = ExchangeMaskingAdditionSystem(input().lines().map(String::toLong), PREAMBLE_SIZE)
}
