package fr.lidonis.adventofcode.y2023.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PipeMazeTest {

    private val input = """
        F-7..
        L-J..
        .S-7.
        .|.|.
        .L-J.
        .....
    """.trimIndent()

    @Test
    fun part1() {
        val pipeMaze = PipeMaze(input.lines())
        val result = pipeMaze.part1()
        assertThat(result).isEqualTo(4)
    }

    private val input2 = """
        7-F7-
        .FJ|7
        SJLL7
        |F--J
        LJ.LJ
    """.trimIndent()

    @Test
    fun part12() {
        val pipeMaze = PipeMaze(input2.lines())
        val result = pipeMaze.part1()
        assertThat(result).isEqualTo(8)
    }

    private val inputP2 = """
        ..........
        .S------7.
        .|F----7|.
        .||OOOO||.
        .||OOOO||.
        .|L-7F-J|.
        .|II||II|.
        .L--JL--J.
        ..........
    """.trimIndent()

    @Test
    fun part2() {
        val pipeMaze = PipeMaze(inputP2.lines())
        val result = pipeMaze.part2()
        assertThat(result).isEqualTo(4)
    }

    private val inputP2Complex = """
        .F----7F7F7F7F-7....
        .|F--7||||||||FJ....
        .||.FJ||||||||L7....
        FJL7L7LJLJ||LJ.L-7..
        L--J.L7...LJS7F-7L7.
        ....F-J..F7FJ|L7L7L7
        ....L7.F7||L7|.L7L7|
        .....|FJLJ|FJ|F7|.LJ
        ....FJL-7.||.||||...
        ....L---J.LJ.LJLJ...
    """.trimIndent()

    @Test
    fun part2Complex() {
        val pipeMaze = PipeMaze(inputP2Complex.lines())
        val result = pipeMaze.part2()
        assertThat(result).isEqualTo(8)
    }

}
