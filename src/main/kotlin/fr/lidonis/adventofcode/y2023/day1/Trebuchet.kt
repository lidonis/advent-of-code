package fr.lidonis.adventofcode.y2023.day1

private val spelledOutDigits = listOf(
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
).withIndex().map { (index, value) -> value to index + 1 }

class Trebuchet(private val lines: List<String>) {

    fun part1() = lines.sumOf { line ->
        line
            .indexDigits()
            .calibration()
    }

    private fun String.indexDigits() = mapIndexedNotNull { index, c ->
        if (c.isDigit()) index to c.digitToInt() else null
    }

    private fun List<Pair<Int, Int>>.calibration(): Int {
        val first = minBy { it.first }.second
        val last = maxBy { it.first }.second
        return "$first$last".toInt()
    }

    fun part2() = lines.sumOf { line ->
        line
            .indexDigitsAndSpelledOutDigits()
            .calibration()
    }

    private fun String.indexDigitsAndSpelledOutDigits() = mapIndexedNotNull { index, c ->
        if (c.isDigit()) {
            index to c.digitToInt()
        } else {
            substring(index).let { sub ->
                spelledOutDigits
                    .find { sub.startsWith(it.first) }
                    ?.let { index to it.second }
            }
        }
    }
}
