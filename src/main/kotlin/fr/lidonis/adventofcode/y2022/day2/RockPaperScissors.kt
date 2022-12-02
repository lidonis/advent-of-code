package fr.lidonis.adventofcode.y2022.day2

class RockPaperScissors(private val lines: List<String>) {

    fun score() = lines.sumOf(::fightShape)
    fun scoreAgain() = lines.sumOf(::fightOutcome)

    private fun fightShape(input: String) = when (input) {
        "A X" -> 4
        "A Y" -> 8
        "A Z" -> 3
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 7
        "C Y" -> 2
        "C Z" -> 6
        else -> 0
    }

    private fun fightOutcome(input: String) = when (input) {
        "A X" -> 3
        "A Y" -> 4
        "A Z" -> 8
        "B X" -> 1
        "B Y" -> 5
        "B Z" -> 9
        "C X" -> 2
        "C Y" -> 6
        "C Z" -> 7
        else -> 0
    }
}
