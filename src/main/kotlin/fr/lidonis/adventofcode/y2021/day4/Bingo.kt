package fr.lidonis.adventofcode.y2021.day4

class Bingo(private val lines: List<String>) {

    private val drawn = lines.first().split(",").map(String::toInt)
    private val boards = lines.drop(1)
        .filter { it.isNotEmpty() }
        .chunked(BINGO_BOARD_SIZE)
        .map { BingoBoard(it) }


    fun finalScoreFirstWin(): Int {
        val allDrawn = mutableListOf<Int>()
        drawn.forEach { draw ->
            allDrawn+= draw
            boards.firstOrNull { it.isWinning(allDrawn) }?.let {
                return it.score(allDrawn)
            }
        }
        error("Score not found")
    }

    fun finalScoreLastWin(): Int {
        var nonWinningBoard = boards
        val allDrawn = mutableListOf<Int>()
        drawn.forEach { draw ->
            allDrawn+= draw
            if(nonWinningBoard.size != 1) {
                nonWinningBoard = nonWinningBoard.filterNot { it.isWinning(allDrawn) }
            } else if(nonWinningBoard.single().isWinning(allDrawn)){
                return nonWinningBoard.single().score(allDrawn)
            }
        }
        error("Score not found")
    }

}
