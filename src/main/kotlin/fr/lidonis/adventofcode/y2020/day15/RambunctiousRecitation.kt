package fr.lidonis.adventofcode.y2020.day15

private const val DELIMITER = ","

class RambunctiousRecitation(text: String) {
    private val numbers = text.split(DELIMITER).map(String::toInt)

    fun lastSpoken(turnToPlay: Int): Int {
        var lastSpoken = numbers.last()
        val history = initHistory()
        for (turn in numbers.size + 1..turnToPlay) {
            history[lastSpoken]?.let { (lastTurn, turnBefore) ->
                lastSpoken = if (turnBefore != null) lastTurn - turnBefore else 0
                historize(history, turn, lastSpoken)
            }
        }
        return lastSpoken
    }

    private fun initHistory() = numbers.foldIndexed(mutableMapOf<Int, Pair<Int, Int?>>()) { index, acc, number ->
        historize(acc, index + 1, number); acc
    }

    private fun historize(history: MutableMap<Int, Pair<Int, Int?>>, turn: Int, number: Int) {
        val turns = history[number]
        history[number] = if (turns == null)
            turn to -1
        else
            turn to turns.first
    }
}
