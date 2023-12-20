package fr.lidonis.adventofcode.y2023.day20

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PulsePropagationTest {

    private val input = """
        broadcaster -> a, b, c
        %a -> b
        %b -> c
        %c -> inv
        &inv -> a
    """.trimIndent()

    @Test
    fun part1() {
        val pulsePropagation = PulsePropagation(input.lines())
        val result = pulsePropagation.part1()
        assertThat(result).isEqualTo(32000000)
    }

    private val interestingExample = """
        broadcaster -> a
        %a -> inv, con
        &inv -> b
        %b -> con
        &con -> output
    """.trimIndent()

    @Test
    fun interestingExample() {
        val pulsePropagation = PulsePropagation(interestingExample.lines())
        val result = pulsePropagation.part1()
        assertThat(result).isEqualTo(11687500)
    }
}
