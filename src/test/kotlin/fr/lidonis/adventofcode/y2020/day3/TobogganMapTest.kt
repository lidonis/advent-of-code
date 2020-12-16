package fr.lidonis.adventofcode.y2020.day3

import fr.lidonis.adventofcode.y2020.day3.TobogganMap.Slope
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TobogganMapTest {

    private val tobogganMap = TobogganMap(
        """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
            """.trimIndent().lines()
    )

    @Test
    fun `Slope Right 3, down 1`() {
        assertThat(tobogganMap.treeEncounter(Slope(3, 1))).isEqualTo(7)
    }

    @Test
    fun `Slope Right 1, down 1`() {
        assertThat(tobogganMap.treeEncounter(Slope(1, 1))).isEqualTo(2)
    }

    @Test
    fun `Slope Right 5, down 1`() {
        assertThat(tobogganMap.treeEncounter(Slope(5, 1))).isEqualTo(3)
    }

    @Test
    fun `Slope Right 7, down 1`() {
        assertThat(tobogganMap.treeEncounter(Slope(7, 1))).isEqualTo(4)
    }

    @Test
    fun `Slope Right 1, down 2`() {
        assertThat(tobogganMap.treeEncounter(Slope(1, 2))).isEqualTo(2)
    }

}