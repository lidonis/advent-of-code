package fr.lidonis.adventofcode.y2023.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CamelCardsTest {

    private val input = """
        32T3K 765
        T55J5 684
        KK677 28
        KTJJT 220
        QQQJA 483
    """.trimIndent()

    @Test
    fun part1() {
        val camelCards = CamelCards(input.lines())
        val result = camelCards.part1()
        assertThat(result).isEqualTo(6440)
    }

    @Test
    fun part2() {
        val camelCards = CamelCards(input.lines())
        val result = camelCards.part2()
        assertThat(result).isEqualTo(5905)
    }

    private val otherInput = """
        2345A 1
        Q2KJJ 13
        Q2Q2Q 19
        T3T3J 17
        T3Q33 11
        2345J 3
        J345A 2
        32T3K 5
        T55J5 29
        KK677 7
        KTJJT 34
        QQQJA 31
        JJJJJ 37
        JAAAA 43
        AAAAJ 59
        AAAAA 61
        2AAAA 23
        2JJJJ 53
        JJJJ2 41
    """.trimIndent()

    @Test
    fun part1Other() {
        val camelCards = CamelCards(otherInput.lines())
        val result = camelCards.part1()
        assertThat(result).isEqualTo(6592)
    }

    @Test
    fun part2Other() {
        val camelCards = CamelCards(otherInput.lines())
        val result = camelCards.part2()
        assertThat(result).isEqualTo(6839)
    }
}
