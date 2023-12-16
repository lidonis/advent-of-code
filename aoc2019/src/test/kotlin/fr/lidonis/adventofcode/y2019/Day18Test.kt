package fr.lidonis.adventofcode.y2019

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class Day18Test {

    @Test
    fun `simple example`() {
        val vault = Day18(
            input = "#########\n" +
                "#b.A.@.a#\n" +
                "#########"
        )
        assertThat(vault.shortestPathStepCount()).isEqualTo(8)
    }

    @Test
    fun `larger example`() {
        val vault = Day18(
            input = "########################\n" +
                "#f.D.E.e.C.b.A.@.a.B.c.#\n" +
                "######################.#\n" +
                "#d.....................#\n" +
                "########################"
        )
        assertThat(vault.shortestPathStepCount()).isEqualTo(86)
    }

    @Test
    fun `more example 1`() {
        val vault = Day18(
            input = "########################\n" +
                "#...............b.C.D.f#\n" +
                "#.######################\n" +
                "#.....@.a.B.c.d.A.e.F.g#\n" +
                "########################"
        )
        assertThat(vault.shortestPathStepCount()).isEqualTo(132)
    }

    @Test
    fun `more example 2`() {
        val vault = Day18(
            input = "#################\n" +
                "#i.G..c...e..H.p#\n" +
                "########.########\n" +
                "#j.A..b...f..D.o#\n" +
                "########@########\n" +
                "#k.E..a...g..B.n#\n" +
                "########.########\n" +
                "#l.F..d...h..C.m#\n" +
                "#################"
        )
        assertThat(vault.shortestPathStepCount()).isEqualTo(136)
    }

    @Test
    fun `more example 3`() {
        val vault = Day18(
            input = "########################\n" +
                "#@..............ac.GI.b#\n" +
                "###d#e#f################\n" +
                "###A#B#C################\n" +
                "###g#h#i################\n" +
                "########################"
        )
        assertThat(vault.shortestPathStepCount()).isEqualTo(81)
    }
}
