package fr.lidonis.adventofcode.y2020.day1

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ExpenseReportTest {

    @Test
    fun `part 1`() {
        val example = """
                        1721
                        979
                        366
                        299
                        675
                        1456
                        """.trimIndent().lines().map(String::toInt)
        val combinationBySum = ExpenseReport(example).findCombinationBySum(2, 2020)
        Assertions.assertThat(combinationBySum).isEqualTo(setOf(1721, 299))
    }

    @Test
    fun `part 2`() {
        val example = """
                        1721
                        979
                        366
                        299
                        675
                        1456
                        """.trimIndent().lines().map(String::toInt)
        val combinationBySum = ExpenseReport(example).findCombinationBySum(3, 2020)
        Assertions.assertThat(combinationBySum).isEqualTo(setOf(979, 366, 675))
    }
}