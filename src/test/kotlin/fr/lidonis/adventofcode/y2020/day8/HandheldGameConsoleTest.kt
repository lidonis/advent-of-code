package fr.lidonis.adventofcode.y2020.day8

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class HandheldGameConsoleTest {

    @Test
    fun `infinite loop`() {
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
        val handheldGameConsole = HandheldGameConsole(program)
        assertThatThrownBy { handheldGameConsole.run() }.isEqualTo(HandheldGameConsole.InfiniteLoopException(5))
    }

    @Test
    fun `normal run`() {
        val program = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            nop -4
            acc +6
        """.trimIndent().lines()
        val handheldGameConsole = HandheldGameConsole(program)
        assertThat(handheldGameConsole.run()).isEqualTo(8)
    }
}