package fr.lidonis.adventofcode.y2020.day9

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExchangeMaskingAdditionSystemTest {

    @Test
    fun `find invalid number`() {
        val numbers = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent().lines().map(String::toLong)
        assertThat(ExchangeMaskingAdditionSystem(numbers, 5).findInvalidNumber()).isEqualTo(127)
    }

    @Test
    fun `find weakness`() {
        val numbers = """
            35
            20
            15
            25
            47
            40
            62
            55
            65
            95
            102
            117
            150
            182
            127
            219
            299
            277
            309
            576
        """.trimIndent().lines().map(String::toLong)
        assertThat(ExchangeMaskingAdditionSystem(numbers, 5).findWeakness()).isEqualTo(62)
    }
}