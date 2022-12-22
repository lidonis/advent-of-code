package fr.lidonis.adventofcode.y2022.day22

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 22

@Suppress("unused")
object Day22 : AdventOfCode2022(DAY) {

    private val monkeyMap = MonkeyMap(input().lines())

    @Answer("51928383302238")
    override fun part1() = monkeyMap.password()

    @Answer("3305669217840")
    override fun part2() = monkeyMap.part2()
}