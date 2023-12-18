package fr.lidonis.adventofcode.y2021.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LanternfishTest {

    private val example = "3,4,3,1,2"

    @Test
    internal fun `reproduce 18`() {
        val lanternfish = Lanternfish(example)
        val result = lanternfish.reproduce(18)
        assertThat(result).isEqualTo(26)
    }

    @Test
    internal fun `reproduce 80`() {
        val lanternfish = Lanternfish(example)
        val result = lanternfish.reproduce(80)
        assertThat(result).isEqualTo(5934)
    }

    @Test
    internal fun `reproduce 256`() {
        val lanternfish = Lanternfish(example)
        val result = lanternfish.reproduce(256)
        assertThat(result).isEqualTo(26984457539)
    }
}
