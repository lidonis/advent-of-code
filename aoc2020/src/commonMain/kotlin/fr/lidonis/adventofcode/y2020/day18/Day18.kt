package fr.lidonis.adventofcode.y2020.day18

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 18

@Suppress("unused")
object Day18 : AdventOfCode2020(DAY) {

    private val operationOrder = OperationOrder(input().readLines())

    @Answer("14208061823964")
    override fun part1() = operationOrder.sum1()

    @Answer("320536571743074")
    override fun part2() = operationOrder.sum2()
}
