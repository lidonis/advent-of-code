package fr.lidonis.adventofcode.y2021.day13

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 13

object Day13 : AdventOfCode2021(DAY) {

    private val transparentOrigami = TransparentOrigami(input().lines())

    @Answer("802")
    override fun part1() = transparentOrigami.countVisibleDotsAfter1Fold()

    @Answer("RKHFZGUB")
    override fun part2() = transparentOrigami.display()
}
