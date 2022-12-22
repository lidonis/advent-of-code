package fr.lidonis.adventofcode.y2021.day10

class SyntaxScoring(private val lines: List<String>) {

    fun scoreCorruptedLines() = lines.map(::scoreCorruptedLine).sum()

    private fun scoreCorruptedLine(value: String): Int {
        val deck = ArrayDeque<Char>()
        for (c in value) {
            when (c) {
                '(', '[', '{', '<' -> deck.addLast(c)
                ')' -> if (deck.last() == '(') deck.removeLast() else return 3
                ']' -> if (deck.last() == '[') deck.removeLast() else return 57
                '}' -> if (deck.last() == '{') deck.removeLast() else return 1197
                '>' -> if (deck.last() == '<') deck.removeLast() else return 25137
            }
        }
        return 0
    }

    fun scoreIncompleteLines(): Long {
        val scores = lines
            .asSequence()
            .filter { scoreCorruptedLine(it) == 0 }
            .map(::incomplete)
            .map(ArrayDeque<Char>::reversed)
            .map {
                it.fold(0L) { acc, c ->
                    acc * 5 + when (c) {
                        '(' -> 1
                        '[' -> 2
                        '{' -> 3
                        '<' -> 4
                        else -> error("Unknown character")
                    }
                }
            }
            .sorted()
            .toList()
        return scores[scores.size / 2]
    }

    private fun incomplete(value: String): ArrayDeque<Char> {
        val deck = ArrayDeque<Char>()
        for (c in value) {
            when (c) {
                '(', '[', '{', '<' -> deck.addLast(c)
                ')' -> if (deck.last() == '(') deck.removeLast()
                ']' -> if (deck.last() == '[') deck.removeLast()
                '}' -> if (deck.last() == '{') deck.removeLast()
                '>' -> if (deck.last() == '<') deck.removeLast()
            }
        }
        return deck
    }
}
