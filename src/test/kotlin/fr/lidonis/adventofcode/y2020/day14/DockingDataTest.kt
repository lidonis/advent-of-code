package fr.lidonis.adventofcode.y2020.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DockingDataTest {

    @Test
    fun v1() {
        val program = """
            mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
            mem[8] = 11
            mem[7] = 101
            mem[8] = 0
        """.trimIndent().lines()
        assertThat(DockingDataV1(program).runProgram()).isEqualTo(165)
    }

    @Test
    fun v2() {
        val program = """
            mask = 000000000000000000000000000000X1001X
            mem[42] = 100
            mask = 00000000000000000000000000000000X0XX
            mem[26] = 1
        """.trimIndent().lines()
        assertThat(DockingDataV2(program).runProgram()).isEqualTo(208)
    }
}