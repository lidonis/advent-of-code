package fr.lidonis.adventofcode.y2022.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DistressSignalTest {

    private val fullExample = """
            [1,1,3,1,1]
            [1,1,5,1,1]

            [[1],[2,3,4]]
            [[1],4]

            [9]
            [[8,7,6]]

            [[4,4],4,4]
            [[4,4],4,4,4]

            [7,7,7,7]
            [7,7,7]

            []
            [3]

            [[[]]]
            [[]]

            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]
        """.trimIndent()

    @Test
    fun `full example`() {
        val result = DistressSignal(fullExample.lines()).countOrdered()

        assertThat(result).isEqualTo(13)
    }

    @Test
    fun `example 1`() {
        val input = """
            [1,1,3,1,1]
            [1,1,5,1,1]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `example 2`() {
        val input = """
            [[1],[2,3,4]]
            [[1],4]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `example 3`() {
        val input = """
            [9]
            [[8,7,6]]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `example 4`() {
        val input = """
            [[4,4],4,4]
            [[4,4],4,4,4]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `example 5`() {
        val input = """
            [7,7,7,7]
            [7,7,7]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `example 6`() {
        val input = """
            []
            [3]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `example 7`() {
        val input = """
            [[[]]]
            [[]]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `example 8`() {
        val input = """
            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]
            """.trimIndent()

        val result = DistressSignal(input.lines()).countOrdered()

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `decoder key`() {
        val result = DistressSignal(fullExample.lines()).decoderKey()

        assertThat(result).isEqualTo(140)
    }
}
