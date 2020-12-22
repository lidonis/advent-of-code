package fr.lidonis.adventofcode.y2020.day22

import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.tail

inline class Deck(private val cards: List<Int>) {
    constructor(text: String) : this(text.split(", ").map(String::toInt))

    val score: Int get() = cards.reversed().mapIndexed { index, value -> value * (index + 1) }.sum()
    val size: Int get() = cards.size
    fun draw() = cards.head to Deck(cards.tail)
    fun copy(size: Int) = Deck(cards.take(size))
    fun isEmpty() = cards.isEmpty()
    operator fun plus(card: Int) = Deck(cards + card)
}