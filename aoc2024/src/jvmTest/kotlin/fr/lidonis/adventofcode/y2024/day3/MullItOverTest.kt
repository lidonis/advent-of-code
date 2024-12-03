package fr.lidonis.adventofcode.y2024.day3

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MullItOverTest {

    private val input = """
        xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
    """.trimIndent()

    private val input2 = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """.trimIndent()

    @Test
    fun part1() {
        val mullItOver = MullItOver(input)

        val result = mullItOver.part1()
        assertThat(result).isEqualTo(161)
    }

    @Test
    fun part2() {
        val mullItOver = MullItOver(input2)
        val result = mullItOver.part2()
        assertThat(result).isEqualTo(48)
    }
}
