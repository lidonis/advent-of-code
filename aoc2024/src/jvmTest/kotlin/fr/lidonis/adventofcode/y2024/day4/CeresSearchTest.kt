package fr.lidonis.adventofcode.y2024.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CeresSearchTest {

    private val input = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()

    @Test
    fun part1() {
        val ceresSearch = CeresSearch(input.lines())
        val result = ceresSearch.part1()
        assertThat(result).isEqualTo(18)
    }

    @Test
    fun part2() {
        val ceresSearch = CeresSearch(input.lines())
        val result = ceresSearch.part2()
        assertThat(result).isEqualTo(9)
    }
}
