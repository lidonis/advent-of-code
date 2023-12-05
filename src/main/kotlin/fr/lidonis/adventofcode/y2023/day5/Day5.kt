package fr.lidonis.adventofcode.y2023.day5

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 5

@Suppress("unused")
object Day5 : AdventOfCode2023(DAY) {

    private val seedFertilizer = SeedFertilizer(input().text())

    @Answer("510109797")
    override fun part1() = seedFertilizer.part1()

    @Answer("9622622")
    override fun part2() = seedFertilizer.part2()
}
