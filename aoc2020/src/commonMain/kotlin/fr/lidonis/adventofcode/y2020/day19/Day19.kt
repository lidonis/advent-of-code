package fr.lidonis.adventofcode.y2020.day19

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 19

@Suppress("unused")
object Day19 : AdventOfCode2020(DAY) {

    private val monsterMessages = MonsterMessages(input().lines())

    @Answer("182")
    override fun part1() = monsterMessages.countValidMessages()

    @Answer("334")
    override fun part2() = monsterMessages.countValidMessagesUpdated()
}
