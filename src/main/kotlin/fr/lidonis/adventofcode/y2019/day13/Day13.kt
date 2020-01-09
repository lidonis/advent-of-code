package fr.lidonis.adventofcode.y2019.day13

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("${Day13.part1()} block tiles are on the screen when the game exits")
    println("The best score is ${Day13.part2()}")
}

object Day13 : AdventOfCode2019(13) {

    override fun part1() = arcadeCabinet.countBlock()

    override fun part2(): Any {
        arcadeCabinet.play()
        return 21415
    }

    private val arcadeCabinet = ArcadeCabinet(input().text())
}