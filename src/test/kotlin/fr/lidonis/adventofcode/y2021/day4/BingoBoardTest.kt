package fr.lidonis.adventofcode.y2021.day4

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BingoBoardTest {

    val example = """
        22 13 17 11  0
         8  2 23  4 24
        21  9 14 16  7
         6 10  3 18  5
         1 12 20 15 19
    """.trimIndent()

    @Test
    internal fun lose() {
        val bingoBoard = BingoBoard(example.lines())
        val result = bingoBoard.isWinning(emptyList())
        assertThat(result).isFalse
    }

    @Test
    internal fun `win row`() {
        val bingoBoard = BingoBoard(example.lines())
        val result = bingoBoard.isWinning(listOf(22,13,17,11,0))
        assertThat(result).isTrue
    }

    @Test
    internal fun `win column`() {
        val bingoBoard = BingoBoard(example.lines())
        val result = bingoBoard.isWinning(listOf(22,8,21,6,1))
        assertThat(result).isTrue
    }

    @Test
    internal fun `win example 2`() {
        val bingoBoard = BingoBoard("""
             3 15  0  2 22
             9 18 13 17  5
            19  8  7 25 23
            20 11 10 24  4
            14 21 16 12  6
        """.trimIndent().lines())
        val result = bingoBoard.isWinning(listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13))
        assertThat(result).isTrue
    }
}
