package fr.lidonis.adventofcode.y2023.day3

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.multiply

typealias Symbol = Pair<Char, Position>

private val NUMBER_REGEX = """\d+""".toRegex()

class GearRatios(private val lines: List<String>) {

    private val symbolsToPartNumbers = lines.parse()
    fun part1() = symbolsToPartNumbers.values.flatten().sum()
    fun part2() = symbolsToPartNumbers
        .filter { it.isGear() }
        .entries.sumOf { it.value.multiply() }

    private fun List<String>.parse(): Map<Symbol, List<Int>> =
        buildMap<Symbol, MutableList<Int>> {
            for ((y, line) in withIndex()) {
                this.parseLine(line, y)
            }
        }

    private fun MutableMap<Symbol, MutableList<Int>>.parseLine(line: String, y: Int) {
        for (match in NUMBER_REGEX.findAll(line)) {
            parsePart(y, match)
        }
    }

    private fun MutableMap<Symbol, MutableList<Int>>.parsePart(y: Int, match: MatchResult) {
        val yStart = (y - 1).coerceAtLeast(0)
        val yEnd = (y + 1).coerceAtMost(lines.lastIndex)
        for (yCheck in yStart..yEnd) {
            checkSymbol(yCheck, match)
        }
    }

    private fun MutableMap<Symbol, MutableList<Int>>.checkSymbol(
        yCheck: Int,
        match: MatchResult
    ) {
        val lineCheck = lines[yCheck]
        val xStart = (match.range.first - 1).coerceAtLeast(0)
        val xEnd = (match.range.last + 1).coerceAtMost(lineCheck.lastIndex)
        for (x in xStart..xEnd) {
            val c = lineCheck[x]
            if (c.isSymbol()) {
                getOrPut(c to Position(x, yCheck)) { mutableListOf() } += match.value.toInt()
            }
        }
    }

    private fun Char.isSymbol(): Boolean = this != '.' && !isDigit()

    private fun Map.Entry<Symbol, List<Int>>.isGear() =
        key.first.isGearSymbol() && value.size == 2

    private fun Char.isGearSymbol(): Boolean = this == '*'
}
