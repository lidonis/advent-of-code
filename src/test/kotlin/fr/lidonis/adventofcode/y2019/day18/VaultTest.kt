package fr.lidonis.adventofcode.y2019.day18

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VaultTest {

    @Test
    fun `simple example`() {
        val input = """
            #########
            #b.A.@.a#
            #########""".trimIndent().lines()
        assertThat(Vault(input).shortestPathStepCount()).isEqualTo(8)
    }

    @Test
    fun `larger example`() {
        val input = """
            ########################
            #f.D.E.e.C.b.A.@.a.B.c.#
            ######################.#
            #d.....................#
            ########################""".trimIndent().lines()
        val vault = Vault(input)
        assertThat(vault.shortestPathStepCount()).isEqualTo(86)
    }

    @Test
    fun `more example 1`() {
        val input = """
            ########################
            #...............b.C.D.f#
            #.######################
            #.....@.a.B.c.d.A.e.F.g#
            ########################""".trimIndent().lines()
        assertThat(Vault(input).shortestPathStepCount()).isEqualTo(132)
    }

    @Test
    fun `more example 2`() {
        val input = """
            #################
            #i.G..c...e..H.p#
            ########.########
            #j.A..b...f..D.o#
            ########@########
            #k.E..a...g..B.n#
            ########.########
            #l.F..d...h..C.m#
            #################""".trimIndent().lines()
        val vault = Vault(input)
        assertThat(vault.shortestPathStepCount()).isEqualTo(136)
    }

    @Test
    fun `more example 3`() {
        val input = """
            ########################
            #@..............ac.GI.b#
            ###d#e#f################
            ###A#B#C################
            ###g#h#i################
            ########################""".trimIndent().lines()
        val vault = Vault(input)
        assertThat(vault.shortestPathStepCount()).isEqualTo(81)
    }
}
