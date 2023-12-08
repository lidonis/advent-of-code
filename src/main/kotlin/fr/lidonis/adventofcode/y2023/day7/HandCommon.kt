package fr.lidonis.adventofcode.y2023.day7

object HandCommon {

    fun classifyHand(
        firstGroupSize: Int,
        secondGroupSize: Int
    ) = when (firstGroupSize) {
        FIVE_OF_A_KIND_SIZE -> HandType.FIVE_OF_A_KIND
        FOUR_OF_A_KIND_SIZE -> HandType.FOUR_OF_A_KIND
        THREE_OF_A_KIND_SIZE -> if (secondGroupSize >= PAIR_SIZE) HandType.FULL_HOUSE else HandType.THREE_OF_A_KIND
        PAIR_SIZE -> if (secondGroupSize >= PAIR_SIZE) HandType.TWO_PAIRS else HandType.ONE_PAIR
        else -> HandType.HIGH_CARD
    }

    fun <T : Comparable<T>> compareHands(
        handType1: HandType,
        handType2: HandType,
        values1: List<T>,
        values2: List<T>
    ): Int {
        val compareTo = handType1.compareTo(handType2)
        return if (compareTo == 0) {
            values1.zip(values2)
                .map { it.first.compareTo(it.second) }
                .firstOrNull { it != 0 } ?: 0
        } else {
            compareTo
        }
    }
}
