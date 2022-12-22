package fr.lidonis.adventofcode.y2021.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SyntaxScoringTest {

    val example = """
        [({(<(())[]>[[{[]{<()<>>
        [(()[<>])]({[<{<<[]>>(
        {([(<{}[<>[]}>{[]{[(<()>
        (((({<>}<{<{<>}{[]{[]{}
        [[<[([]))<([[{}[[()]]]
        [{[{({}]{}}([{[{{{}}([]
        {<[[]]>}<{[{[{[]{()[[[]
        [<(<(<(<{}))><([]([]()
        <{([([[(<>()){}]>(<<{{
        <{([{{}}[<[[[<>{}]]]>[]]
    """.trimIndent()

    @Test
    internal fun `score corrupted lines`() {
        val syntaxScoring = SyntaxScoring(example.lines())
        val result = syntaxScoring.scoreCorruptedLines()
        assertThat(result).isEqualTo(26397)
    }

    @Test
    internal fun `score incomplete lines`() {
        val syntaxScoring = SyntaxScoring(example.lines())
        val result = syntaxScoring.scoreIncompleteLines()
        assertThat(result).isEqualTo(288957)
    }
}