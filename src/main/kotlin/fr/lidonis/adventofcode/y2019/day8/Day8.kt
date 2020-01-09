package fr.lidonis.adventofcode.y2019.day8

import InputReader

fun main() {
    val inputs = InputReader("day8.txt").lines().map { line -> line.split(",") }
    val decoder = ImageDecoder(inputs[0][0], 25, 6)
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

    //TODO OCR from int array
}

