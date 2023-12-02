package fr.lidonis.adventofcode.y2020.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ShuttleSearchTest {

    private val input1 = """
        939
        7,13,x,x,59,x,31,19
    """.trimIndent().lines()

    @Test
    fun searchBus() {
        assertThat(ShuttleSearch(input1).searchBus()).isEqualTo(59 to 5)
    }

    @Test
    fun `find timestamp 1`() {
        assertThat(ShuttleSearch(input1).findTimestamp()).isEqualTo(1068781)
    }

    @Test
    fun `find timestamp 2`() {
        val input = """
            0
            17,x,13,19
        """.trimIndent().lines()
        assertThat(ShuttleSearch(input).findTimestamp()).isEqualTo(3417)
    }

    @Test
    fun `find timestamp 3`() {
        val input = """
            0
            67,7,59,61
        """.trimIndent().lines()
        assertThat(ShuttleSearch(input).findTimestamp()).isEqualTo(754018)
    }

    @Test
    fun `find timestamp 4`() {
        val input = """
            0
            67,x,7,59,61
        """.trimIndent().lines()
        assertThat(ShuttleSearch(input).findTimestamp()).isEqualTo(779210)
    }

    @Test
    fun `find timestamp 5`() {
        val input = """
            0
            67,7,x,59,61
        """.trimIndent().lines()
        assertThat(ShuttleSearch(input).findTimestamp()).isEqualTo(1261476)
    }

    @Test
    fun `find timestamp 6`() {
        val input = """
            0
            1789,37,47,1889
        """.trimIndent().lines()
        assertThat(ShuttleSearch(input).findTimestamp()).isEqualTo(1202161486)
    }
}
