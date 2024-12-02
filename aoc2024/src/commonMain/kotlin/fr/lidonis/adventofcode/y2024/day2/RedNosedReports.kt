package fr.lidonis.adventofcode.y2024.day2

import kotlin.math.absoluteValue

private const val MIN_SAFE_DIFFERENCE = 1
private const val MAX_SAFE_DIFFERENCE = 3

class RedNosedReports(lines: List<String>) {

    val reports = lines.map { it.split(" ").map { it.toInt() } }

    fun part1() =
        reports.count { report ->
            report.isSafe()
        }

    fun part2() =
        reports.count { report ->
            (0 until report.size).any { index ->
                report.removeAtIndex(index).isSafe()
            }
        }

    private fun List<Int>.isSafe() =
        zipWithNext().run {
            isMonotonic() && hasValidDifferences()
        }

    private fun List<Pair<Int, Int>>.isMonotonic(): Boolean {
        fun isIncreasing() = all { (a, b) -> a < b }
        fun isDecreasing() = all { (a, b) -> a > b }
        return isIncreasing() || isDecreasing()
    }

    private fun List<Pair<Int, Int>>.hasValidDifferences() =
        all { (a, b) -> (a - b).absoluteValue in MIN_SAFE_DIFFERENCE..MAX_SAFE_DIFFERENCE }

    private fun List<Int>.removeAtIndex(index: Int): List<Int> =
        toMutableList().apply { this@apply.removeAt(index) }
}
