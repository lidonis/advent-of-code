package fr.lidonis.adventofcode.y2019.day19

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("${Day19.part1()} are affected by the tractor beam in the 50x50 area closest to the emitter")
    println("The value is ${Day19.part2()}")
}

object Day19 : AdventOfCode2019(19) {

    override fun part1() = tractorBeam.countAffected(50)
    override fun part2() = tractorBeam.fit(100)

    private val tractorBeam = TractorBeam(input().text())
}

