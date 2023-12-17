package fr.lidonis.adventofcode.y2023.day13

import fr.lidonis.adventofcode.common.transposed

private const val HORIZONTAL_MULTIPLIER = 100

class PointOfIncidence(private val lines: String) {

    private val patterns = lines.split("\n\n").map {
        Pattern(it.split("\n"))
    }

    fun part1() = patterns.sumOf {
        it.summarize(
            compare = { s1, s2 -> if (s1 == s2) 0 else 1 },
            result = 0
        )
    }

    fun part2() = patterns.sumOf {
        it.summarize(
            compare = { s1, s2 -> s1.zip(s2).count { (c1, c2) -> c1 != c2 } },
            result = 1
        )
    }

    data class Pattern(val pattern: List<String>) {

        fun summarize(compare: (String, String) -> Int, result: Int) =
            horizontalReflection(compare, result)?.let { it * HORIZONTAL_MULTIPLIER }
                ?: verticalReflection(compare, result)
                ?: error("No reflection found")

        private fun horizontalReflection(compare: (String, String) -> Int, result: Int) =
            pattern.findReflection(compare, result)

        private fun verticalReflection(compare: (String, String) -> Int, result: Int) =
            pattern.transposed().findReflection(compare, result)

        private fun List<String>.findReflection(compare: (String, String) -> Int, result: Int) =
            (1 until size)
                .firstOrNull { lines ->
                    take(lines).reversed().zip(drop(lines), compare).sum() == result
                }
    }
}
