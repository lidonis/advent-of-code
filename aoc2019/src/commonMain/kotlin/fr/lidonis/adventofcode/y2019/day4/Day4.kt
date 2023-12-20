package fr.lidonis.adventofcode.y2019.day4

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 4

@Suppress("unused")
object Day4 : AdventOfCode2019(DAY) {

    private val inputRange = input()
        .split("-")
        .map(String::toInt)
        .let { (start, end) -> start..end }

    @Answer("511")
    override fun part1() = inputRange.count(PasswordChecker::check1)

    @Answer("316")
    override fun part2() = inputRange.count(PasswordChecker::check2)
}
