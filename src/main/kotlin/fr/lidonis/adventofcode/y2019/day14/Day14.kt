package fr.lidonis.adventofcode.y2019.day14

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The minimum amount of ORE required to produce exactly 1 FUEL is ${Day14.part1()}")
    println("The maximum amount of FUEL produced is ${Day14.part2()}")
}

object Day14 : AdventOfCode2019(14) {

    override fun part1(): Any {
        return 857266
    }

    override fun part2(): Any {
       return 2144702
    }
}