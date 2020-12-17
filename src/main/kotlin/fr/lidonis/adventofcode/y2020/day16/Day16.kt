package fr.lidonis.adventofcode.y2020.day16

import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 16

object Day16 : AdventOfCode2020(DAY) {

    private val ticketTranslation = TicketTranslation(input().lines())

    override fun part1() = ticketTranslation.scanningErrorRate()

    override fun part2() = ticketTranslation.ticket()
        .filterKeys { it.startsWith("departure") }.values
        .fold(1, Long::times)
}
