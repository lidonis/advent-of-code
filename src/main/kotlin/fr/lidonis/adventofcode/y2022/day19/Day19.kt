package fr.lidonis.adventofcode.y2022.day19

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022
import kotlinx.coroutines.runBlocking

private const val DAY = 19

@Suppress("unused")
object Day19 : AdventOfCode2022(DAY) {

    private val notEnoughMinerals = NotEnoughMinerals(input().lines())

    @Answer("1150")
    override fun part1() = notEnoughMinerals.sumOfQualityLevels()

    @Answer("37367")
    override fun part2() = runBlocking { fr.lidonis.adventofcode.y2022.day19.test.Day19(input().lines()).part2() }
}
