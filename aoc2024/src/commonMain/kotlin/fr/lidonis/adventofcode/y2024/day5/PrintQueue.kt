package fr.lidonis.adventofcode.y2024.day5

class PrintQueue(lines: List<String>) {
    private val parsed = Parser(lines)

    private fun compareRules(left: Int, right: Int): Int {
        val rightRule = parsed.rulesGrouped[right]
        val leftRule = parsed.rulesGrouped[left]
        return when {
            rightRule != null && left in rightRule -> 1
            leftRule != null && right in leftRule -> -1
            else -> 0
        }
    }

    val partition = parsed.updates.partition {
        it.isSortedWith(Comparator<Int>(this::compareRules))
    }

    fun part1() = partition.first
        .sumOf { it.middleValue }

    fun part2() = partition.second
        .map { it.sortedWith(Comparator(this::compareRules)) }
        .sumOf { it.middleValue }

    private val List<Int>.middleValue: Int
        get() = this[size / 2]

    private fun <T> List<T>.isSortedWith(comparator: Comparator<T>) =
        asSequence().zipWithNext()
            .all { comparator.compare(it.first, it.second) <= 0 }

    private class Parser(lines: List<String>) {
        private val partition = lines
            .asSequence()
            .filter { it.isNotBlank() }
            .partition { string -> string.contains(RULE_DELIMITER) }

        val rulesGrouped = partition.first
            .map { string -> string.split(RULE_DELIMITER).map { it.toInt() } }.map { it[0] to it[1] }
            .groupBy(
                keySelector = { it.first },
                valueTransform = { it.second }
            )

        val updates = partition.second.map { string -> string.split(UPDATE_DELIMITER).map { it.toInt() } }

        companion object {
            private const val RULE_DELIMITER = "|"
            private const val UPDATE_DELIMITER = ","
        }
    }
}
