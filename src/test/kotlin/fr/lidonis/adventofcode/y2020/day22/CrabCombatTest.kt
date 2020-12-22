package fr.lidonis.adventofcode.y2020.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CrabCombatTest {

    private val crabCombat = CrabCombat(
        """
        Player 1:
        9
        2
        6
        3
        1

        Player 2:
        5
        8
        4
        7
        10
    """.trimIndent()
    )

    @Test
    fun play() {
        assertThat(crabCombat.play()).isEqualTo(306)
    }

    @Test
    fun playRecursive() {
        assertThat(crabCombat.playRecursive()).isEqualTo(291)
    }

    @Test
    internal fun `infinite game`() {
        val crabCombat = CrabCombat(
            """
            Player 1:
            43
            19

            Player 2:
            2
            29
            14
        """.trimIndent()
        )
        assertThat(crabCombat.playRecursive()).isEqualTo(105)
    }
}