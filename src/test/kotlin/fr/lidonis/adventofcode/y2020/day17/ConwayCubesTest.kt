package fr.lidonis.adventofcode.y2020.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConwayCubesTest {

    @Test
    fun name() {
        val state = """
            .#.
            ..#
            ###
        """.trimIndent().lines()
        assertThat(ConwayCubes(state).cube(1)).isEqualTo(11)
    }
}