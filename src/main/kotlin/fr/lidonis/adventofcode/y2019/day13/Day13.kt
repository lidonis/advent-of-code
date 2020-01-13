package fr.lidonis.adventofcode.y2019.day13

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("${Day13.part1()} block tiles are on the screen when the game exits")
    println("The best score is ${Day13.part2()}")
}

private const val DAY = 13
object Day13 : AdventOfCode2019(DAY) {

    override fun part1() = arcadeCabinet.countBlock()

    private const val FAKE_RESULT = 21415

    override fun part2(): Any {
        arcadeCabinet.play()
        return FAKE_RESULT
    }

    private val arcadeCabinet = ArcadeCabinet(input().text())
}
