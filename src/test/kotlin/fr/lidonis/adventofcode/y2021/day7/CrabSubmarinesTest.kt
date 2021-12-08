package fr.lidonis.adventofcode.y2021.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CrabSubmarinesTest {

    val example = "16,1,2,0,4,2,7,1,2,14"

    @Test
    fun `fuel to align constant burn`() {
        assertThat(CrabSubmarines(example).fuelToAlignConstantBurn()).isEqualTo(37)
    }

    @Test
    fun `fuel to align increasing burn`() {
        assertThat(CrabSubmarines(example).fuelToAlignIncreasingBurn()).isEqualTo(168)
    }
}
