package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 24

object Day24 : AdventOfCode2019(DAY) {

    override fun part1(): Int {
        val evolutions = mutableSetOf<Int>()
        var eris = Eris(scan)
        while (evolutions.add(eris.biodiversityRating)) {
            eris = eris.evolve()
        }
        return eris.biodiversityRating
    }

    private const val EVOLVE_COUNT = 200

    override fun part2() =
        RecursiveEris(listOf(Eris(scan).toRecursive())).evolve(EVOLVE_COUNT).countBugs()

    private val scan = input().text()
}
