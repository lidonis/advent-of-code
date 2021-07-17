package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SeaMonsterChaserTest {

    private val image = """
        .#.#..#.##...#.##..#####
        ###....#.#....#..#......
        ##.##.###.#.#..######...
        ###.#####...#.#####.#..#
        ##.#....#.##.####...#.##
        ...########.#....#####.#
        ....#..#...##..#.#.###..
        .####...#..#.....#......
        #..#.##..#..###.#.##....
        #.####..#.####.#.#.###..
        ###.#.#...#.######.#..##
        #.####....##..########.#
        ##..##.#...#...#.#.#.#..
        ...#..#..#.#.##..###.###
        .#.#....#.##.#...###.##.
        ###.#...#..#.##.######..
        .#.#.###.##.##.#..#.##..
        .####.###.#...###.#..#.#
        ..#.#..#..#.#.#.####.###
        #..####...#.#.#.###.###.
        #####..#####...###....##
        #.##..#..#...#..####...#
        .#.###..##..##..####.##.
        ...###...##...#...#..###
    """.trimIndent().lines()

    private fun readLines(list: List<String>) = sequence {
        for ((i, line) in list.withIndex()) {
            for ((j, c) in line.withIndex()) {
                if (c == '#') yield(Position(i, j))
            }
        }
    }.toSet()

    @Test
    fun findSeaMonster() {
        val monsters = SeaMonsterChaser().findSeaMonsters(readLines(image))
        assertThat(monsters.size).isEqualTo(30)
    }

}
