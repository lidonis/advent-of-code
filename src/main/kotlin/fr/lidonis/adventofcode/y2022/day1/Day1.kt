package fr.lidonis.adventofcode.y2022.day1

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 1

private const val TOP = 3

@Suppress("unused")
object Day1 : AdventOfCode2022(DAY) {

    private val calorieCounting = CalorieCounting(input().text())

    @Answer("67622")
    override fun part1() = calorieCounting.mostCalories(1)

    @Answer("201491")
    override fun part2() = calorieCounting.mostCalories(TOP)

}
