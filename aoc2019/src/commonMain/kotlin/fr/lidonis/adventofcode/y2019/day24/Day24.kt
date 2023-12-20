package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 24

@Suppress("unused")
object Day24 : AdventOfCode2019(DAY) {

    private const val EVOLVE_COUNT = 200

    private val scan = input().readText()

    @Answer("18370591")
    override fun part1(): Int {
        val evolutions = mutableSetOf<Int>()
        val looped = Eris(scan).evolutions().dropWhile {
            evolutions.add(it.biodiversityRating)
        }.first()
        return looped.biodiversityRating
    }

    @Answer("2040")
    override fun part2() = RecursiveEris(scan).evolve(EVOLVE_COUNT).countBugs()
}
