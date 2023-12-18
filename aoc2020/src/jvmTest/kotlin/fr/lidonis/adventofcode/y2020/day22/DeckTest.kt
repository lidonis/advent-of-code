package fr.lidonis.adventofcode.y2020.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    private val deck = Deck("3, 2, 10, 6, 8, 5, 9, 4, 7, 1")

    @Test
    fun score() {
        assertThat(deck.score).isEqualTo(306)
    }

    @Test
    fun testDraw() {
        assertThat(deck.draw().first).isEqualTo(3)
        assertThat(deck.draw().second.score).isEqualTo(276)
    }

    @Test
    fun equals() {
        assertThat(deck.copy(deck.size)).isEqualTo(deck)
    }

    @Test
    fun notEquals() {
        assertThat(deck.copy(deck.size - 1)).isNotEqualTo(deck)
    }
}
