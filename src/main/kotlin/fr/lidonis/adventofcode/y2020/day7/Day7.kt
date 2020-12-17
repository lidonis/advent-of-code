package fr.lidonis.adventofcode.y2020.day7

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 7

object Day7 : AdventOfCode2020(DAY) {

    private val luggageProcessing = LuggageProcessing(input().lines())

    override fun part1() = luggageProcessing.countCanContain("shiny gold")

    override fun part2() = luggageProcessing.requiredBagCount("shiny gold")
}
