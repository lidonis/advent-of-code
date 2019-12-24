import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.IntStream


class Day22Test {

    @Nested
    inner class SpaceCardDeckTest {

        private val startDeck = SpaceCardDeck(10)

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

        @Test
        fun `example 1`() {
            val shuffledDeck = startDeck.shuffle(
                """
            deal with increment 7
            deal into new stack
            deal into new stack
            """.trimIndent()
            )
            assertThat(shuffledDeck).containsExactlyElementsOf(listOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7))
        }

        @Test
        fun `example 2`() {
            val shuffledDeck = startDeck.shuffle(
                """
            cut 6
            deal with increment 7
            deal into new stack
            """.trimIndent()
            )
            assertThat(shuffledDeck).containsExactlyElementsOf(listOf(3, 0, 7, 4, 1, 8, 5, 2, 9, 6))
        }

        @Test
        fun `example 3`() {
            val shuffledDeck = startDeck.shuffle(
                """
            deal with increment 7
            deal with increment 9
            cut -2
            """.trimIndent()
            )
            assertThat(shuffledDeck).containsExactlyElementsOf(listOf(6, 3, 0, 7, 4, 1, 8, 5, 2, 9))
        }

        @Test
        fun `example 4`() {
            val shuffledDeck = startDeck.shuffle(
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
            )
            assertThat(shuffledDeck).containsExactlyElementsOf(listOf(9, 2, 5, 8, 1, 4, 7, 0, 3, 6))
        }

        @Test
        fun `part 1`() {
            val deck = SpaceCardDeck(10007)
            val shuffledDeck = deck.shuffle(InputReader("day22.txt").text)
            assertThat(shuffledDeck.cardPosition(2019)).isEqualTo(7171)
        }
    }

    @Nested
    inner class SpaceCardInverseShufflerTest {

        private val startDeck = SpaceCardDeck(10)

        @Test
        fun `deal into new stack`() {
            val deck = apply(startDeck.dealIntoNewStack()) { deck -> deck.dealIntoNewStack() }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        private fun apply(
            spaceCardDeck: SpaceCardDeck,
            function: (SpaceCardInverseShuffler) -> SpaceCardInverseShuffler
        ): List<Int> {
            return spaceCardDeck.mapIndexed { i, v ->
                function(SpaceCardInverseShuffler(10, i, v))
            }.sortedBy { it.index }.map { it.value }
        }


        @Test
        fun `deal into new stack twice`() {
            val deck = apply(startDeck) { deck -> deck.dealIntoNewStack().dealIntoNewStack() }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        @Test
        fun cut() {
            val deck = apply(startDeck.cut(3)) { deck -> deck.cut(3) }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        @Test
        fun `cut negative`() {
            val deck = apply(startDeck.cut(-4)) { deck -> deck.cut(-4) }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        @Test
        fun `deal with increment 3`() {
            val deck = apply(startDeck.dealWithIncrement(3)) { deck -> deck.dealWithIncrement(3) }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        @Test
        fun example1() {
            val instructions = """
                deal with increment 7
                deal into new stack
                deal into new stack
                """.trimIndent()
            val deck = apply(startDeck.shuffle(instructions)) { deck -> deck.shuffle(instructions) }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        @Test
        fun `example 2`() {
            val instructions = """
                cut 6
                deal with increment 7
                deal into new stack
                """.trimIndent()
            val deck = apply(startDeck.shuffle(instructions)) { deck -> deck.shuffle(instructions) }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        @Test
        fun `example 3`() {
            val instructions = """
                deal with increment 7
                deal with increment 9
                cut -2
                """.trimIndent()
            val deck = apply(startDeck.shuffle(instructions)) { deck -> deck.shuffle(instructions) }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

        @Test
        fun `example 4`() {
            val instructions = """
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
            """.trimIndent().trimIndent()
            val deck = apply(startDeck.shuffle(instructions)) { deck -> deck.shuffle(instructions) }
            assertThat(deck).containsExactlyElementsOf(startDeck)
        }

//        @Test
//        fun `part 1`() {
//            val startDeck = SpaceCardDeck(10007)
//            val instructions = InputReader("day22.txt").text
//            val deck = apply(startDeck.shuffle(instructions)) { deck -> deck.shuffle(instructions) }
//            assertThat(deck).containsExactlyElementsOf(startDeck)
//        }

    }

    @Nested
    inner class SpaceCardMathShufflerTest {

        private val startDeck = SpaceCardDeck(10)

        @Test
        fun `deal into new stack`() {
            val deck = apply(startDeck) { deck -> deck.dealIntoNewStack() }
            assertThat(deck).containsExactlyElementsOf(startDeck.dealIntoNewStack())
        }

        @ParameterizedTest
        @MethodSource("Day22Test#rangeProvider")
        fun `cut N`(n: Int) {
            val deck = apply(startDeck) { deck -> deck.cut(n) }
            assertThat(deck).containsExactlyElementsOf(startDeck.cut(n))
        }

        @ParameterizedTest
        @ValueSource(ints = [1, 3, 7, 9])
        fun `deal with increment N`(n: Int) {
            val deck = apply(startDeck) { deck -> deck.dealWithIncrement(n) }
            assertThat(deck).containsExactlyElementsOf(startDeck.dealWithIncrement(n))
        }

        private fun apply(
            spaceCardDeck: SpaceCardDeck,
            function: (SpaceCardMathShuffler) -> Int
        ): List<Int> {
            return spaceCardDeck.mapIndexed { i, v ->
                function(SpaceCardMathShuffler(10, i, v))
            }
        }

    }

    companion object Provider {
        @JvmStatic
        fun rangeProvider() = 0..9
    }
}
