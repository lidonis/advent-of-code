package fr.lidonis.adventofcode.y2019.day8

import fr.lidonis.adventofcode.common.InputReader

private const val IMAGE_WIDTH = 25
private const val IMAGE_HEIGHT = 6

fun main() {
    val inputs = InputReader("input/y2019/day8.txt").lines().map { it.split(",") }
    val decoder = ImageDecoder(inputs[0][0], IMAGE_WIDTH, IMAGE_HEIGHT)
    println(decoder.checksum())
    decoder.decode().map {
        it.map { c ->
            when (c) {
                '0' -> ' '
                '1' -> '█'
                else -> c
            }
        }.joinToString("")
    }.forEach { println(it) }
}
