package fr.lidonis.adventofcode.y2019.day4

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 4

object Day4 : AdventOfCode2019(DAY) {

    private val inputRange = input().text()
        .split("-")
        .map(String::toInt)
        .let { (start, end) -> start..end }

    override fun part1() = inputRange.filter(PasswordChecker::check1).count()

    override fun part2() = inputRange.filter(PasswordChecker::check2).count()
}
