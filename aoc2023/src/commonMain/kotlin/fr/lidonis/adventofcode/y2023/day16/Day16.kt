package fr.lidonis.adventofcode.y2023.day16

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2023.AdventOfCode2023

private const val DAY = 16

@Suppress("unused")
object Day16 : AdventOfCode2023(DAY) {

    private val theFloorWillBeLava = TheFloorWillBeLava(input().readLines())

    @Answer("8901")
    override fun part1() = theFloorWillBeLava.part1()

    @Answer("9064")
    override fun part2() = theFloorWillBeLava.part2()
}
