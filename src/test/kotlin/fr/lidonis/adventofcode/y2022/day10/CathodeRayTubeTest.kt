package fr.lidonis.adventofcode.y2022.day10

import fr.lidonis.adventofcode.common.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CathodeRayTubeTest {

    @Test
    fun `small program`() {
        val input = """
        noop
        addx 3
        addx -5
        """.trimIndent()

        val result = CathodeRayTube(input.lines()).cycle().take(6).drop(1).toList()

        assertThat(result).containsExactly(1, 1, 4, 4, -1)
    }

    @Test
    fun `larger program`() {
        val input = InputReader("/y2022/day10/larger-program.txt")

        val result = CathodeRayTube(input.lines()).signalStrengths()

        assertThat(result).containsExactly(420, 1140, 1800, 2940, 2880, 3960)
    }

    @Test
    fun `larger program print`() {
        val input = InputReader("/y2022/day10/larger-program.txt")

        val result = CathodeRayTube(input.lines()).print()

        assertThat(result).isEqualTo(
        """
            ##..##..##..##..##..##..##..##..##..##..
            ###...###...###...###...###...###...###.
            ####....####....####....####....####....
            #####.....#####.....#####.....#####.....
            ######......######......######......####
            #######.......#######.......#######.....
            
            """.trimIndent())
    }
}
