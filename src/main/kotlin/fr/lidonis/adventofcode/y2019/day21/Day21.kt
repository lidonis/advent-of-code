package fr.lidonis.adventofcode.y2019.day21

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("${Day21.part1()} hull damage in walk mode")
    println("${Day21.part2()} hull damage in run mode")
}

private const val DAY = 21

object Day21 : AdventOfCode2019(DAY) {

    override fun part1() = droid.walk() ?: error("Can't find hull damage")
    override fun part2() = droid.run() ?: error("Can't find hull damage")

    private val droid = SpringDroid(input().text())
}
