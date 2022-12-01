package fr.lidonis.adventofcode.y2022.day1

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CalorieCountingTest {

    private val input = """
            1000
            2000
            3000

            4000

            5000
            6000

            7000
            8000
            9000

            10000
        """.trimIndent()

    @Test
    internal fun testMostCalories() {
        val result = CalorieCounting(input).mostCalories(1)

        Assertions.assertThat(result).isEqualTo(24000)
    }

    @Test
    internal fun testTop3MostCalories() {
        val result = CalorieCounting(input).mostCalories(3)

        Assertions.assertThat(result).isEqualTo(45000)
    }
}
