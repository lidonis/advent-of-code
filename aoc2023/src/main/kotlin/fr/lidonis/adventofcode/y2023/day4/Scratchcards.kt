package fr.lidonis.adventofcode.y2023.day4

import fr.lidonis.adventofcode.common.math.pow

class Scratchcards(private val lines: List<String>) {

    private val cards = lines.map { Scratchcard.parseLine(it) }

    fun part1() = cards.sumOf { it.points() }

    fun part2(): Int {
        val cardsWithCount = cards.map { it to 1 }.toMutableList()
        for (i in lines.indices) {
            val (card, count) = cardsWithCount[i]
            val winningNumbersCount = card.calculateWinningNumbersCount()
            for (j in (i + 1)..(i + winningNumbersCount)) {
                val (card2, count2) = cardsWithCount[j]
                cardsWithCount[j] = card2 to count2 + count
            }
        }
        return cardsWithCount.sumOf { it.second }
    }

    private data class Scratchcard(val winningNumbers: Set<Int>, val numbers: Set<Int>) {
        fun calculateWinningNumbersCount() = (winningNumbers.intersect(numbers)).size

        fun points() = 2.pow(calculateWinningNumbersCount() - 1)

        companion object {
            fun parseLine(line: String): Scratchcard {
                val (_, numbersPart) = line.split(":")
                val (winningNumbers, numbers) = numbersPart.split("|")
                return Scratchcard(
                    winningNumbers = winningNumbers.extractNumbers(),
                    numbers = numbers.extractNumbers()
                )
            }

            private fun String.extractNumbers() =
                split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
        }
    }
}
