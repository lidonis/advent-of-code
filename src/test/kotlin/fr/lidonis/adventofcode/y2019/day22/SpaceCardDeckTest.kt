package fr.lidonis.adventofcode.y2019.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

internal class SpaceCardDeckTest {

    private val startDeck = SpaceCardDeck(10)

    @Nested
    inner class SpaceCardDeckTest {

        @Test
        fun `space deck`() {
            assertThat(startDeck).containsExactlyElementsOf((0 until 10))
        }

        @Test
        fun `deal into new stack`() {
            assertThat(startDeck.dealIntoNewStack()).containsExactlyElementsOf((9 downTo 0))
        }

        @Test
        fun cut() {
            assertThat(startDeck.cut(3)).containsExactlyElementsOf(listOf(3, 4, 5, 6, 7, 8, 9, 0, 1, 2))
        }

        @Test
        fun `cut negative`() {
            assertThat(startDeck.cut(-4)).containsExactlyElementsOf(listOf(6, 7, 8, 9, 0, 1, 2, 3, 4, 5))
        }

        @Test
        fun `deal with increment 3`() {
            assertThat(startDeck.dealWithIncrement(3)).containsExactlyElementsOf(listOf(0, 7, 4, 1, 8, 5, 2, 9, 6, 3))
        }

        @ParameterizedTest
        @MethodSource("fr.lidonis.adventofcode.y2019.day22.SpaceCardDeckTest#examples")
        fun example(example: Pair<String, List<Int>>) {
            val shuffledDeck = startDeck.shuffle(example.first)
            assertThat(shuffledDeck).containsExactlyElementsOf(example.second)
        }

    }

    @Nested
    inner class SpaceCardMathShufflerTest {

        @Test
        fun `deal into new stack`() {
            val deck = apply(startDeck.size) { deck -> deck.dealIntoNewStack() }
            assertThat(deck).containsExactlyElementsOf(startDeck.dealIntoNewStack())
        }

        @ParameterizedTest
        @MethodSource("fr.lidonis.adventofcode.y2019.day22.SpaceCardDeckTest#rangeProvider")
        fun `cut N`(n: Long) {
            val deck = apply(startDeck.size) { deck -> deck.cut(n) }
            assertThat(deck).containsExactlyElementsOf(startDeck.cut(n.toInt()))
        }

        @ParameterizedTest
        @MethodSource("fr.lidonis.adventofcode.y2019.day22.SpaceCardDeckTest#rangeProvider")
        fun `cut -N`(n: Long) {
            val deck = apply(startDeck.size) { deck -> deck.cut(-n) }
            assertThat(deck).containsExactlyElementsOf(startDeck.cut(-n.toInt()))
        }

        @ParameterizedTest
        @ValueSource(ints = [1, 3, 7, 9])
        fun `deal with increment N`(n: Long) {
            val deck = apply(startDeck.size) { deck -> deck.dealWithIncrement(n) }
            assertThat(deck).containsExactlyElementsOf(startDeck.dealWithIncrement(n.toInt()))
        }

        @ParameterizedTest
        @MethodSource("fr.lidonis.adventofcode.y2019.day22.SpaceCardDeckTest#examples")
        fun example(example: Pair<String, List<Int>>) {
            val shuffledDeck = apply(startDeck.size) { deck -> deck.shuffle(example.first) }
            assertThat(shuffledDeck).containsExactlyElementsOf(example.second)
        }

        private fun apply(
            size: Int,
            function: (SpaceCardMathShuffler) -> SpaceCardMathShuffler.ModularFunction
        ) = (0L until size).map { i ->
            function(SpaceCardMathShuffler(size.toLong())).compute(i).toInt()
        }

    }

    companion object {

        @JvmStatic
        fun examples() = listOf(
            """
                    deal with increment 7
                    deal into new stack
                    deal into new stack
                    """.trimIndent()
                    to
                    listOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7),
            """
                    cut 6
                    deal with increment 7
                    deal into new stack
                    """.trimIndent()
                    to
                    listOf(3, 0, 7, 4, 1, 8, 5, 2, 9, 6),
            """
                    deal with increment 7
                    deal with increment 9
                    cut -2
                    """.trimIndent()
                    to
                    listOf(6, 3, 0, 7, 4, 1, 8, 5, 2, 9),
            """
                    deal into new stack
                    cut -2
                    deal with increment 7
                    cut 8
                    cut -4
                    deal with increment 7
                    cut 3
                    deal with increment 9
                    deal with increment 3
                    cut -1
                    """.trimIndent()
                    to
                    listOf(9, 2, 5, 8, 1, 4, 7, 0, 3, 6)
        )

        @JvmStatic
        fun rangeProvider() = 0..9

    }
}
