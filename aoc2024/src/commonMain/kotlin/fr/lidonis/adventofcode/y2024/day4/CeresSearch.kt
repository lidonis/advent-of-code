package fr.lidonis.adventofcode.y2024.day4

class CeresSearch(private val lines: List<String>) {

    fun part1() = findWord(lines, "XMAS")

    fun findWord(grid: List<String>, target: String): Int {
        val numRows = grid.size
        val numCols = grid[0].length

        fun isWithinBounds(x: Int, y: Int) = x in 0 until numRows && y in 0 until numCols

        fun searchFromPosition(startRow: Int, startCol: Int, deltaRow: Int, deltaCol: Int): Boolean {
            var currentRow = startRow
            var currentCol = startCol
            for (character in target) {
                if (!isWithinBounds(currentRow, currentCol) || grid[currentRow][currentCol] != character) return false
                currentRow += deltaRow
                currentCol += deltaCol
            }
            return true
        }

        val directions = listOf(
            Pair(0, 1), // Right
            Pair(0, -1), // Left
            Pair(1, 0), // Down
            Pair(-1, 0), // Up
            Pair(1, 1), // Down-right
            Pair(-1, -1), // Up-left
            Pair(1, -1), // Down-left
            Pair(-1, 1) // Up-right
        )

        fun countMatches(row: Int, col: Int): Int {
            var localCount = 0
            for ((deltaRow, deltaCol) in directions) {
                if (searchFromPosition(row, col, deltaRow, deltaCol)) {
                    localCount++
                }
            }
            return localCount
        }

        return (0 until numRows).asSequence().flatMap { row ->
            (0 until numCols).asSequence().map { col ->
                countMatches(row, col)
            }
        }.sum()
    }

    fun part2() = countXShapeMAS(lines)

    fun countXShapeMAS(matrix: List<String>) =
        (1 until matrix.size - 1).asSequence().sumOf { rowIdx ->
            (1 until matrix[0].length - 1).asSequence().count { colIdx ->
                checkAInXShape(matrix, rowIdx, colIdx)
            }
        }

    private fun checkAInXShape(matrix: List<String>, rowIdx: Int, colIdx: Int) =
        matrix[rowIdx][colIdx] == 'A' && isValidXShape(matrix, rowIdx, colIdx)

    fun isValidXShape(matrix: List<String>, row: Int, col: Int): Boolean {
        val topLeft = matrix[row - 1][col - 1]
        val topRight = matrix[row - 1][col + 1]
        val bottomLeft = matrix[row + 1][col - 1]
        val bottomRight = matrix[row + 1][col + 1]
        val pattern = "$topLeft$topRight$bottomLeft$bottomRight"
        val validXShapes = setOf("MMSS", "SSMM", "SMSM", "MSMS")
        return pattern in validXShapes
    }
}
