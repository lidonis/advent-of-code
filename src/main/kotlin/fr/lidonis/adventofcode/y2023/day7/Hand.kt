package fr.lidonis.adventofcode.y2023.day7

class Hand(private val input: String) : Comparable<Hand> {

    fun type(): HandType {
        val groupsSize = input
            .groupBy { it }
            .map { it.value.size }
            .sortedDescending()
        return HandCommon.classifyHand(groupsSize.first(), groupsSize.getOrNull(1) ?: 0)
    }

    private val values = this.input.map { Value.fromString(it) }

    override operator fun compareTo(other: Hand) =
        HandCommon.compareHands(type(), other.type(), values, other.values)

    enum class Value(private val c: Char) {
        ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'),

        EIGHT('8'), NINE('9'), TEN('T'), JACK('J'), QUEEN('Q'), KING('K'), ACE('A');

        companion object {
            fun fromString(input: Char): Value {
                return entries.first { it.c == input }
            }
        }
    }
}
