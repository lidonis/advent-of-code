package fr.lidonis.adventofcode.y2020.day16

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.common.multiply
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 16

@Suppress("unused")
object Day16 : AdventOfCode2020(DAY) {

    private val ticketTranslation = TicketTranslation(input().lines())

    @Answer("19060")
    override fun part1() = ticketTranslation.scanningErrorRate()

    @Answer("953713095011")
    override fun part2() = ticketTranslation.ticket()
        .filterKeys { it.startsWith("departure") }.values
        .multiply()
}
