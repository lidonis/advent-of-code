package fr.lidonis.adventofcode.y2023.day7

const val FIVE_OF_A_KIND_SIZE = 5
const val FOUR_OF_A_KIND_SIZE = 4
const val THREE_OF_A_KIND_SIZE = 3
const val PAIR_SIZE = 2

enum class HandType {
    HIGH_CARD, ONE_PAIR, TWO_PAIRS, THREE_OF_A_KIND, FULL_HOUSE, FOUR_OF_A_KIND, FIVE_OF_A_KIND
}
