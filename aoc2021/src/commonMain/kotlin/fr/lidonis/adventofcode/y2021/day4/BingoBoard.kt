package fr.lidonis.adventofcode.y2021.day4

import fr.lidonis.adventofcode.common.math.Matrix

const val BINGO_BOARD_SIZE = 5

class BingoBoard(private val lines: List<String>) {
    private val matrix = Matrix(lines)
    private val rows = (1..BINGO_BOARD_SIZE).map { matrix.row(it) }
    private val columns = (1..BINGO_BOARD_SIZE).map { matrix.column(it) }
    private val results = rows + columns
    private val allNumbers = rows.flatten().toSet()

    fun isWinning(drawn: List<Int>) = results.any { drawn.containsAll(it) }
    fun score(currentDraw: List<Int>): Int = (allNumbers - currentDraw).sum() * currentDraw.last()

    override fun toString(): String {
        return "BingoBoard(\n${lines.joinToString("\n")}\n)"
    }
}
