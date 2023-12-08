package fr.lidonis.adventofcode.y2023.day7

import fr.lidonis.adventofcode.y2023.day7.HandCommon.classifyHand

class HandJoker(private val input: String) : Comparable<HandJoker> {

    fun type(): HandType {
        val jokerCount = input.count { it == Value.JOKER.charRepresentation }
        val groupsSize = input
            .filter { it != Value.JOKER.charRepresentation }
            .groupBy { it }
            .map { it.value.size }
            .sortedDescending()
        if (groupsSize.isEmpty()) {
            return HandType.FIVE_OF_A_KIND
        }
        return classifyHand(groupsSize.first() + jokerCount, groupsSize.getOrNull(1) ?: 0)
    }

    private val values = this.input.map { Value.fromString(it) }

    override operator fun compareTo(other: HandJoker) =
        HandCommon.compareHands(type(), other.type(), values, other.values)

    enum class Value(val charRepresentation: Char) {
        JOKER('J'), ONE('1'), TWO('2'), THREE('3'), FOUR('4'), FIVE('5'), SIX('6'), SEVEN('7'),
        EIGHT('8'), NINE('9'), TEN('T'), QUEEN('Q'), KING('K'), ACE('A');

        companion object {
            fun fromString(input: Char): Value {
                return entries.first { it.charRepresentation == input }
            }
        }
    }
}
