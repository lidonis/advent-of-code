package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("Biodiversity rating for the first layout that appears twice ${Day24.part1()}")
    println("${Day24.part2()} bugs are present after 200 minutes")
}

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

    override fun part2() =
        RecursiveEris(listOf(Eris(scan).toRecursive())).evolve(200).count()

    private val scan = input().text()

}
