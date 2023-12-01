package fr.lidonis.adventofcode.y2023.day1

private const val MIN_DIGIT = 1
private const val MAX_DIGIT = 9

class Trebuchet(private val lines: List<String>) {

    private val digits: Map<String, Int> = (MIN_DIGIT..MAX_DIGIT).associateBy { it.toString() }

    private val letters: Map<String, Int> = listOf(
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    ).withIndex().associate { it.value to it.index + 1 }

    private val digitsAndLetters = digits + letters

    fun part1() = lines.map { line ->
        line.calibration(digits)
    }

    private fun String.calibration(digitsAndLetters: Map<String, Int>): Int {
        val first = digitsAndLetters.indexFirst(this).minBy { it.first }.second
        val last = digitsAndLetters.indexLast(this).minBy { it.first }.second
        return "$first$last".toInt()
    }

    private fun Map<String, Int>.indexFirst(line: String) = map {
        line.indexOf(it.key) to it.value
    }.filter { it.first != -1 }

    private fun Map<String, Int>.indexLast(line: String): List<Pair<Int, Int>> {
        val reversedLine = line.reversed()
        return map {
            reversedLine.indexOf(it.key.reversed()) to it.value
        }.filter { it.first != -1 }
    }

    fun part2() = lines.map { line ->
        line.calibration(digitsAndLetters)
    }

}
