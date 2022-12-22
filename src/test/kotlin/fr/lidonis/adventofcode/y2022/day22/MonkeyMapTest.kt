package fr.lidonis.adventofcode.y2022.day22

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MonkeyMapTest {

    val lines = """
                ...#
                .#..
                #...
                ....
        ...#.......#
        ........#...
        ..#....#....
        ..........#.
                ...#....
                .....#..
                .#......
                ......#.
        
        10R5L5R10L4R5L5
    """.trimIndent().lines()

    @Test
    fun `find password`() {
        val result = MonkeyMap(lines).password()

        assertThat(result).isEqualTo(6032)
    }
}