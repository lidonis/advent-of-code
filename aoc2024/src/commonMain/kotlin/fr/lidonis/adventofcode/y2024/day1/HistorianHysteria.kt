package fr.lidonis.adventofcode.y2024.day1

import kotlin.math.absoluteValue

class HistorianHysteria(lines: List<String>) {

    val lists = parseInputLines(lines)

    fun part1() = lists
        .let { (a, b) ->
            a.sorted().zip(b.sorted())
        }.sumOf { (a, b) -> (a - b).absoluteValue }

    fun part2(): Int {
        val (left, right) = lists
        val occurrences = right.groupingBy { it }.eachCount()
        return left.sumOf { it * occurrences.getOrDefault(it, 0) }
    }

    private fun parseInputLines(lines: List<String>) =
        lines.asSequence()
            .map { it.split("   ") }
            .map { (a, b) -> a.toInt() to b.toInt() }
            .unzip()
}
