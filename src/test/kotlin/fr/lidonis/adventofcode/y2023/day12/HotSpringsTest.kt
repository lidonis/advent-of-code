package fr.lidonis.adventofcode.y2023.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HotSpringsTest {

    private val input = """
        ???.### 1,1,3
        .??..??...?##. 1,1,3
        ?#?#?#?#?#?#?#? 1,3,1,6
        ????.#...#... 4,1,1
        ????.######..#####. 1,6,5
        ?###???????? 3,2,1
    """.trimIndent()

    @Test
    fun part1() {
        val hotSprings = HotSprings(input.lines())
        val result = hotSprings.part1()
        assertThat(result).isEqualTo(21)
    }

    @Test
    fun part2() {
        val hotSprings = HotSprings(input.lines())
        val result = hotSprings.part2()
        assertThat(result).isEqualTo(525152)
    }
}
