package fr.lidonis.adventofcode.y2023.day9

typealias History = List<Int>

class MirageMaintenance(lines: List<String>) {

    private val histories = lines.map { line -> line.split(" ").map { it.toInt() } }

    private val differences = histories.map { it.computeDifferences() }

    fun part1() = differences.sumOf { it.next() }

    private fun List<History>.next() = foldRight(0) { history, acc -> history.last() + acc }

    private fun History.computeDifferences() =
        generateSequence(this) { history ->
            history.zipWithNext { a, b -> b - a }
        }.takeWhile { history ->
            history.any { it != 0 }
        }.toList()

    fun part2() = differences.sumOf { it.previous() }

    private fun List<History>.previous() = foldRight(0) { history, acc -> history.first() - acc }
}
