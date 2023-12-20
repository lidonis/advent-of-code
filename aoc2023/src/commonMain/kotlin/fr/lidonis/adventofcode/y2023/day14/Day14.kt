package fr.lidonis.adventofcode.y2023.day14

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 14

@Suppress("unused")
object Day14 : AdventOfCode2023(DAY) {

    private val parabolicReflectorDish = ParabolicReflectorDish(input().readLines())

    @Answer("109385")
    override fun part1() = parabolicReflectorDish.part1()

    @Answer("93102")
    override fun part2() = parabolicReflectorDish.part2()
}
