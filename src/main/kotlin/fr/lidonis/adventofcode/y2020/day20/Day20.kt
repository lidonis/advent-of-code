package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 20

object Day20 : AdventOfCode2020(DAY) {

    private val jurassicJigsaw = JurassicJigsaw(input().text())

    @Answer("32287787075651")
    override fun part1() = multiplyCornerTiles()

    @Answer("1939")
    override fun part2() = jurassicJigsaw.waterRoughness()

    private fun multiplyCornerTiles() = jurassicJigsaw.getCornerTiles().fold(1L) { acc, tile -> acc * tile.id }
}
