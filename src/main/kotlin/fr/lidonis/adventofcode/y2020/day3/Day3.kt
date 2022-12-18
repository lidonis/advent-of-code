package fr.lidonis.adventofcode.y2020.day3

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020
import fr.lidonis.adventofcode.y2020.day3.TobogganMap.Slope

private const val DAY = 3

@Suppress("unused")
object Day3 : AdventOfCode2020(DAY) {

    private const val MIN_RIGHT_SLOPE = 1
    private const val FIRST_RIGHT_SLOPE = 3
    private const val FASTER_RIGHT_SLOPE = 5
    private const val EVEN_FASTER_RIGHT_SLOPE = 7
    private const val MIN_DOWN_SLOPE = 1
    private const val FASTER_DOWN_SLOPE = 2

    private val tobogganMap = TobogganMap(input().lines())

    @Answer("299")
    override fun part1() = tobogganMap.treeEncounter(Slope(FIRST_RIGHT_SLOPE, MIN_DOWN_SLOPE))

    @Answer("3621285278")
    override fun part2() =
        listOf(
            Slope(MIN_RIGHT_SLOPE, MIN_DOWN_SLOPE),
            Slope(FIRST_RIGHT_SLOPE, MIN_DOWN_SLOPE),
            Slope(FASTER_RIGHT_SLOPE, MIN_DOWN_SLOPE),
            Slope(EVEN_FASTER_RIGHT_SLOPE, MIN_DOWN_SLOPE),
            Slope(MIN_RIGHT_SLOPE, FASTER_DOWN_SLOPE)
        )
            .map { tobogganMap.treeEncounter(it).toLong() }
            .reduce(Long::times)
}
