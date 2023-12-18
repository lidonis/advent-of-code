package fr.lidonis.adventofcode.y2022.day4

class CampCleanup(private val input: List<String>) {
    private val ranges = input.map { line ->
        line.split(",").map {
            it.split("-")
                .map(String::toInt)
                .let { (a, b) -> (a..b).toSet() }
        }
    }

    fun contains() = ranges.count { (s1, s2) -> s1.containsAll(s2) || s2.containsAll(s1) }
    fun overlap() = ranges.count { (s1, s2) -> s1.any { it in s2 } }
}
