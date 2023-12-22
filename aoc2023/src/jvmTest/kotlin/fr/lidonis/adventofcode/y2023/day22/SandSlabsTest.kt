package fr.lidonis.adventofcode.y2023.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SandSlabsTest {

    private val input = """
        1,0,1~1,2,1
        0,0,2~2,0,2
        0,2,3~2,2,3
        0,0,4~0,2,4
        2,0,5~2,2,5
        0,1,6~2,1,6
        1,1,8~1,1,9
    """.trimIndent()

    @Test
    fun part1() {
        val sandSlabs = SandSlabs(input.lines())
        val result = sandSlabs.part1()
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun part2() {
        val sandSlabs = SandSlabs(input.lines())
        val result = sandSlabs.part2()
        assertThat(result).isEqualTo(0)
    }
}
