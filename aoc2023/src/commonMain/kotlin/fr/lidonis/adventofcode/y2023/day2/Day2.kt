package fr.lidonis.adventofcode.y2023.day2

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 2

@Suppress("unused")
object Day2 : AdventOfCode2023(DAY) {

    private val cubeConundrum = CubeConundrum(input().readLines())

    @Answer("2551")
    override fun part1() = cubeConundrum.part1()

    @Answer("62811")
    override fun part2() = cubeConundrum.part2()
}
