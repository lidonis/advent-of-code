package fr.lidonis.adventofcode.y2022.day21

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 21

@Suppress("unused")
object Day21 : AdventOfCode2022(DAY) {

    private val monkeyMath = MonkeyMath(input().lines())

    @Answer("51928383302238")
    override fun part1() = monkeyMath.root()

    @Answer("3305669217840")
    override fun part2() = monkeyMath.human()
}
