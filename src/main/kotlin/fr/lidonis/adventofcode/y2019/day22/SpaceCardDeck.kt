package fr.lidonis.adventofcode.y2019.day22

import java.util.*

class SpaceCardDeck(private val cards: List<Int>) : Iterable<Int> by cards {
    constructor(size: Int) : this((0 until size).toList())

    val size: Int get() = cards.size

    fun dealIntoNewStack() = SpaceCardDeck(cards.reversed())

    fun cut(n: Int) = SpaceCardDeck(cards.toMutableList().also {
        Collections.rotate(it, -n)
    })

    fun dealWithIncrement(n: Int): SpaceCardDeck {
        val newCards = IntArray(size)
        var i = 0
        for (card in cards) {
            newCards[i] = card
            i = (i + n) % size
        }
        return SpaceCardDeck(newCards.toList())
    }

    fun shuffle(instructions: String) = instructions.lines().map {
        when {
            it == "deal into new stack" -> { deck: SpaceCardDeck -> deck.dealIntoNewStack() }
            it.startsWith("cut") -> { deck: SpaceCardDeck -> deck.cut(it.split(" ").last().toInt()) }
            it.startsWith("deal with increment") ->
                { deck: SpaceCardDeck -> deck.dealWithIncrement(it.split(" ").last().toInt()) }
            else -> { _ -> error("Unknown technique") }
        }
    }.fold(this) { acc, function -> function(acc) }

    fun cardPosition(position: Int) = cards.indexOf(position)

    override fun toString() = "SpaceCardDeck(cards=$cards)"

}
