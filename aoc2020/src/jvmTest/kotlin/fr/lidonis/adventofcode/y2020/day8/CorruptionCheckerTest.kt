package fr.lidonis.adventofcode.y2020.day8

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CorruptionCheckerTest {

    @Test
    fun corrupted() {
        val program = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent().lines()
        val corruptionChecker = CorruptionChecker(program)
        Assertions.assertThat(corruptionChecker.run()).isEqualTo(8)
    }
}
