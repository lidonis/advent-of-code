package fr.lidonis.adventofcode.y2020.day15

private const val DELIMITER = ","

class RambunctiousRecitation(text: String) {
    private val numbers = text.split(DELIMITER).map(String::toInt)
    private var lastSpoken = numbers.last()
    private val history = numbers.foldIndexed(mutableMapOf<Int, Pair<Int, Int?>>()) { index, acc, number ->
        historize(acc, index + 1, number); acc
    }

    private fun historize(map: MutableMap<Int, Pair<Int, Int?>>, turn: Int, number: Int) {
        val turns = map[number]
        map[number] = if (turns == null)
            turn to null
        else
            turn to turns.first
    }

    fun lastSpoken(turnToPlay: Int): Int {
        for (turn in numbers.size + 1..turnToPlay) {
            history[lastSpoken]?.let { (lastTurn, turnBefore) ->
                lastSpoken = if (turnBefore != null) lastTurn - turnBefore else 0
                historize(history, turn, lastSpoken)
            }
        }
        return lastSpoken
    }
}
