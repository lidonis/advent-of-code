package fr.lidonis.adventofcode.y2022.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NoSpaceLeftOnDeviceTest {

    @Test
    internal fun dirSmallerThan100000() {
        val input = """
        ${'$'} cd /
        ${'$'} ls
        dir a
        14848514 b.txt
        8504156 c.dat
        dir d
        ${'$'} cd a
        ${'$'} ls
        dir e
        29116 f
        2557 g
        62596 h.lst
        ${'$'} cd e
        ${'$'} ls
        584 i
        ${'$'} cd ..
        ${'$'} cd ..
        ${'$'} cd d
        ${'$'} ls
        4060174 j
        8033020 d.log
        5626152 d.ext
        7214296 k   
        """.trimIndent()

        val result = NoSpaceLeftOnDevice(input.lines()).smallerThan(100_000)

        assertThat(result).containsExactly(94853, 584)
    }
}
