package fr.lidonis.adventofcode.y2023.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LensLibraryTest {

    private val input = "rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7"

    @Test
    fun part1() {
        val lensLibrary = LensLibrary(input)
        val result = lensLibrary.part1()
        assertThat(result).isEqualTo(1320)
    }

    @Test
    fun part2() {
        val lensLibrary = LensLibrary(input)
        val result = lensLibrary.part2()
        assertThat(result).isEqualTo(145)
    }
}
