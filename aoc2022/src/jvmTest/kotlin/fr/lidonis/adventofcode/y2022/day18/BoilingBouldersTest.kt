package fr.lidonis.adventofcode.y2022.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BoilingBouldersTest {

    private val simpleInput = """
            1,1,1
            2,1,1
    """.trimIndent()

    private val largerInput = """
            2,2,2
            1,2,2
            3,2,2
            2,1,2
            2,3,2
            2,2,1
            2,2,3
            2,2,4
            2,2,6
            1,2,5
            3,2,5
            2,1,5
            2,3,5
    """.trimIndent()

    @Test
    fun `simple example surface area`() {
        val result = BoilingBoulders(simpleInput.lines()).surfaceArea()

        assertThat(result).isEqualTo(10)
    }

    @Test
    fun `larger example surface area`() {
        val result = BoilingBoulders(largerInput.lines()).surfaceArea()

        assertThat(result).isEqualTo(64)
    }

    @Test
    fun `simple example exterior surface area`() {
        val result = BoilingBoulders(simpleInput.lines()).exteriorSurfaceArea()

        assertThat(result).isEqualTo(10)
    }

    @Test
    fun `larger example exterior surface area`() {
        val result = BoilingBoulders(largerInput.lines()).exteriorSurfaceArea()

        assertThat(result).isEqualTo(58)
    }
}
