package fr.lidonis.adventofcode.y2023.day7

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 7

@Suppress("unused")
object Day7 : AdventOfCode2023(DAY) {

    private val camelCards = CamelCards(input().readLines())

    @Answer("256448566")
    override fun part1() = camelCards.part1()

    @Answer("254412181")
    override fun part2() = camelCards.part2()
}
