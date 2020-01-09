package fr.lidonis.adventofcode.y2019.day7

import fr.lidonis.adventofcode.y2019.AdventOfCode2019

fun main() {
    println("The largest output signal that can be sent to the thrusters is ${Day7.part1()}")
    println("The highest signal that can be sent to the thrusters is ${Day7.part2()}")
}

object Day7 : AdventOfCode2019(7) {

    override fun part1() = largestSignal(program, (0L..4)) ?: error("No signal ...")
    override fun part2() = largestSignal(program, (5L..9)) ?: error("No signal ...")

    private fun largestSignal(program: String, range: LongRange) = range.toList().permute().map {
        Amplifiers(program, it).run()
    }.max()

    private val program = input().text()
}

fun <T> List<T>.permute(): List<List<T>> {
    if (this.size == 1) return listOf(this)
    val permutations = mutableListOf<List<T>>()
    val toInsert = this[0]
    for (permutation in this.drop(1).permute()) {
        for (i in 0..permutation.size) {
            val newPermutation = permutation.toMutableList()
            newPermutation.add(i, toInsert)
            permutations.add(newPermutation)
        }
    }
    return permutations
}