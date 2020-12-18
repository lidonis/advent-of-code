package fr.lidonis.adventofcode.y2019.day21

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 21

object Day21 : AdventOfCode2019(DAY) {

    private val droid = SpringDroid(input().text())

    @Answer("19357507")
    override fun part1() = droid.walk() ?: error("Can't find hull damage")

    @Answer("1142830249")
    override fun part2() = droid.run() ?: error("Can't find hull damage")
}
