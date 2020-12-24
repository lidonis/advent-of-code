package fr.lidonis.adventofcode.y2020.day24

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 24

private const val NB_EVOLUTION = 100

object Day24 : AdventOfCode2020(DAY) {

    private val lobbyLayout = LobbyLayout.fromLines(input().lines())

    @Answer("495")
    override fun part1() = lobbyLayout.countBlackTiles()

    @Answer("4012")
    override fun part2() = lobbyLayout.evolve(NB_EVOLUTION).countBlackTiles()
}
