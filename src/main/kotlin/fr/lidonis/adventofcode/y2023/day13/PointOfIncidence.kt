package fr.lidonis.adventofcode.y2023.day13

class PointOfIncidence(private val lines: String) {

    fun part1() {
        val patterns = lines.split("\n\n").map {
            Pattern(it.split("\n"))
        }
        patterns.forEach(Pattern::sym)
    }

    fun part2() = lines.length

    data class Pattern(val pattern: List<String>) {
        fun sym() {
            val symHo = pattern.zipWithNext().mapIndexedNotNull { i, (s1, s2) ->
                if (s1 == s2) {
                    i + 1
                } else {
                    null
                }
            }
            println(symHo)
        }
    }
}
