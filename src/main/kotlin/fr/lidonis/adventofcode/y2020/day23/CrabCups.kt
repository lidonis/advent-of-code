package fr.lidonis.adventofcode.y2020.day23

private const val PICKUP_SIZE = 3

class CrabCups(text: String, private val size: Int = text.length) {

    private val values = IntArray(size)
    private var current = 0

    init {
        val numbers = text.map { it.toString().toInt() - 1 } + (text.length until size)
        numbers.zipWithNext().forEach { values[it.first] = it.second }
        val first = numbers.first()
        values[numbers.last()] = first
        current = first
    }

    fun play(nbMoves: Int = 1) {
        repeat(nbMoves) { move() }
    }

    private fun move() {
        val pickUp = pickUp()
        val destination = destination(pickUp)
        val next = values[destination]
        values[current] = values[pickUp.last()]
        values[destination] = pickUp.first()
        values[pickUp.last()] = next
        current = values[current]
    }

    private fun pickUp(): List<Int> {
        val pickUp = mutableListOf<Int>()
        var cup = current
        repeat(PICKUP_SIZE) {
            val value = values[cup]
            pickUp += value
            cup = value
        }
        return pickUp
    }

    private fun destination(pickUp: List<Int>): Int {
        var destination = current
        do {
            destination -= 1
            if (destination < 0) destination = size - 1
        } while (destination in pickUp)
        return destination
    }

    val order: String
        get() : String {
            return generateSequence(values[0]) { values[it] }
                .takeWhile { it != 0 }
                .fold(StringBuilder()) { sb, value -> sb.append(value + 1) }.toString()
        }

    fun cupNextTo(cup: Int) = values[cup - 1] + 1
}
