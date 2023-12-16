package fr.lidonis.adventofcode.y2022.day23

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 23

private const val NB_ROUND_PART_1 = 10

@Suppress("unused")
object Day23 : AdventOfCode2022(DAY) {

    private val unstableDiffusion = UnstableDiffusion.parse(input().text())

    @Answer("4025")
    override fun part1() = unstableDiffusion.evolve(NB_ROUND_PART_1).countEmptyTile()

    @Answer("935")
    override fun part2() = unstableDiffusion.numberOfTheFirstRoundWhereNoElfMoves()
}
