package fr.lidonis.adventofcode.y2020.day10

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AdapterArrayTest {

    private val example1 = """
                16
                10
                15
                5
                1
                11
                7
                19
                6
                12
                4
    """.trimIndent().lines().map(String::toInt)

    private val example2: List<Int> = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent().lines().map(String::toInt)

    @Test
    fun `differences 1`() {
        assertThat(AdapterArray(example1).differences()).isEqualTo(7 * 5)
    }

    @Test
    fun `differences 2`() {
        assertThat(AdapterArray(example2).differences()).isEqualTo(22 * 10)
    }

    @Test
    fun `arrangements 1`() {
        assertThat(AdapterArray(example1).arrangements()).isEqualTo(8)
    }

    @Test
    fun `arrangements 2`() {
        assertThat(AdapterArray(example2).arrangements()).isEqualTo(19208)
    }
}
