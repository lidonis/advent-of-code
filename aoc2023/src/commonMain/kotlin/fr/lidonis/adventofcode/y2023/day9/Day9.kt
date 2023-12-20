package fr.lidonis.adventofcode.y2023.day9

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 9

@Suppress("unused")
object Day9 : AdventOfCode2023(DAY) {

    private val mirageMaintenance = MirageMaintenance(input().readLines())

    @Answer("1806615041")
    override fun part1() = mirageMaintenance.part1()

    @Answer("1211")
    override fun part2() = mirageMaintenance.part2()
}
