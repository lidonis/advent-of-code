package fr.lidonis.adventofcode.y2019.day4

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("${Day4.part1()} different passwords with first check")
    print("${Day4.part2()} different passwords with second check")
}

object Day4 : AdventOfCode2019(4) {

    override fun part1() = inputRange.filter(PasswordChecker::check1).count()
    override fun part2() = inputRange.filter(PasswordChecker::check2).count()

    private val inputRange = input().text()
        .split("-")
        .map(String::toInt).run {
            this[0]..this[1]
        }

}


