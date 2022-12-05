package fr.lidonis.adventofcode.y2022.day3

private const val NB_ELVES = 3

class RucksackReorganization(private val lines: List<String>) {
    fun rucksack() = lines.sumOf { line ->
        val (rucksack1, rucksack2) = line.chunked(line.length / 2).map { it.toSet() }
        rucksack1.intersect(rucksack2).single().priority
    }

    fun elves() = lines.chunked(NB_ELVES).sumOf {
        it.reduce(String::intersect).single().priority
    }
}

private const val LOWER_CASE_START = 1
private const val UPPER_CASE_START = 27

private val Char.priority: Int
    get() = when {
        isLowerCase() -> code - 'a'.code + LOWER_CASE_START
        isUpperCase() -> code - 'A'.code + UPPER_CASE_START
        else -> error("Only letters allowed")
    }

private fun String.intersect(other: String) = this.toSet().intersect(other.toSet()).joinToString("")
