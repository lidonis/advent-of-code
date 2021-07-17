package fr.lidonis.adventofcode.y2020.day8

import fr.lidonis.adventofcode.y2020.day8.HandheldGameConsole.InfiniteLoopException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HandheldGameConsoleTest {

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
        val expectedFailure = Result.failure<InfiniteLoopException>(InfiniteLoopException(5))
        assertThat(handheldGameConsole.run()).isEqualTo(expectedFailure)
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
        assertThat(handheldGameConsole.run()).isEqualTo(Result.success(8))
    }
}
