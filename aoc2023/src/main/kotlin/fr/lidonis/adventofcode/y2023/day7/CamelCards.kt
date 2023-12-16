package fr.lidonis.adventofcode.y2023.day7

class CamelCards(private val lines: List<String>) {

    fun part1() = totalWinnings(::Hand)

    private fun <T : Comparable<T>> totalWinnings(constructor: (String) -> T) =
        lines
            .map { line ->
                val split = line.split(" ")
                constructor(split[0]) to split[1].toInt()
            }
            .sortedBy { it.first }
            .mapIndexed { index, pair ->
                pair.second * (index + 1)
            }.sum()

    fun part2(): Int = totalWinnings(::HandJoker)
}
