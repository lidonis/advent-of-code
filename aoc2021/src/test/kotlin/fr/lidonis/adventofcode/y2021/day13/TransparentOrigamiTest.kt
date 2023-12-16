package fr.lidonis.adventofcode.y2021.day13

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

internal class TransparentOrigamiTest {

    val example = """
        6,10
        0,14
        9,10
        0,3
        10,4
        4,11
        6,0
        6,12
        4,1
        0,13
        10,12
        3,4
        3,0
        8,4
        1,10
        2,14
        8,10
        9,0

        fold along y=7
        fold along x=5
    """.trimIndent()

    @Test
    internal fun `fold 1`() {
        val transparentOrigami = TransparentOrigami(example.lines())
        val result = transparentOrigami.countVisibleDotsAfter1Fold()
        assertThat(result).isEqualTo(17)
    }

    @Test
    @Disabled
    internal fun display() {
        val transparentOrigami = TransparentOrigami(example.lines())
        val result = transparentOrigami.display()
        assertThat(result).isEqualTo("O")
    }
}
