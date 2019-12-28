package fr.lidonis.adventofcode.y2019.day7

import InputReader

fun main() {
    println("The largest output signal that can be sent to the thrusters is ${Day7.part1()}")
    println("The highest signal that can be sent to the thrusters is ${Day7.part2()}")
}

object Day7 {

    fun part1() = largestSignal(program())
    fun part2() = highestSignal(program())

    private fun largestSignal(program: List<Long>) = (0L..4).toList().permute().map {
        Amplifiers(
                program, it
        ).run()
    }.max()

    private fun highestSignal(program: List<Long>) = (5L..9).toList().permute().map {
        Amplifiers(
                program, it
        ).run()
    }.max()
    private fun program() = InputReader("day7.txt").asLineOfLongs()

}

fun <T> List<T>.permute(): List<List<T>> {
    if (this.size == 1) return listOf(this)
    val perms = mutableListOf<List<T>>()
    val toInsert = this[0]
    for (perm in this.drop(1).permute()) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}