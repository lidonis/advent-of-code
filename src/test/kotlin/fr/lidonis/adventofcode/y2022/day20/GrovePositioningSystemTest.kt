package fr.lidonis.adventofcode.y2022.day20

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GrovePositioningSystemTest {

    val lines = """
            1
            2
            -3
            3
            -2
            0
            4
        """.trimIndent()
        .lines()

    @Test
    fun sum() {
        val grovePositioningSystem = GrovePositioningSystem(lines)
        val result = grovePositioningSystem.sum()
        assertThat(result).isEqualTo(3)
    }

    @Test
    fun `sum decrypted`() {
        val grovePositioningSystem = GrovePositioningSystem(lines)
        val result = grovePositioningSystem.sumDecrypted()
        assertThat(result).isEqualTo(1623178306)
    }
}
