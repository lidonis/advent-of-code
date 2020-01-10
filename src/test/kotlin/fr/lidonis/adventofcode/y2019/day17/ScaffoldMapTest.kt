package fr.lidonis.adventofcode.y2019.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScaffoldMapTest {

    @Test
    fun `test example alignement parameters`() {
        val scaffoldMap = ScaffoldMap.from(
            """
            ..#..........
            ..#..........
            #######...###
            #.#...#...#.#
            #############
            ..#...#...#..
            ..#####...^..
            """.trimIndent()
        )
        assertThat(scaffoldMap.sumOfTheAlignmentParameters()).isEqualTo(76)
    }

    @Test
    fun `test find path`() {
        val scaffoldMap = ScaffoldMap.from(
            """
            #######...#####
            #.....#...#...#
            #.....#...#...#
            ......#...#...#
            ......#...###.#
            ......#.....#.#
            ^########...#.#
            ......#.#...#.#
            ......#########
            ........#...#..
            ....#########..
            ....#...#......
            ....#...#......
            ....#...#......
            ....#####......
            """.trimIndent()
        )
        assertThat(scaffoldMap.findPath().joinToString(",")).isEqualTo("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2")
    }


}