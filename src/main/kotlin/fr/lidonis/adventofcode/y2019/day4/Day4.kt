package fr.lidonis.adventofcode.y2019.day4

import InputReader


fun main() {
    println("${Day4.part1()} different passwords with first check")
    print("${Day4.part2()} different passwords with second check")
}

object Day4 {

    fun part1() = input().filter(PasswordChecker::check1).count()
    fun part2() = input().filter(PasswordChecker::check2).count()

    private fun input(): IntRange {
        val values = InputReader("day4.txt").text.split("-").map { it.toInt() }
        return values[0]..values[1]
    }

}


