package fr.lidonis.adventofcode.y2020.day5

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BinaryBoardingScannerTest {

    @Test
    fun `find highest seat ID`() {
        val scan = """
            BFFFBBFRRR
            FFFBBBFRRR
            BBFFBBFRLL
        """.trimIndent()
        val binaryBoardingScanner = BinaryBoardingScanner(scan.lines())
        assertThat(binaryBoardingScanner.highestSeatID()).isEqualTo(820)
    }
}