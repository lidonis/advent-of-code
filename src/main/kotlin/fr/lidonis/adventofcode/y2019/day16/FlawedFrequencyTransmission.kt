package fr.lidonis.adventofcode.y2019.day16

import kotlin.math.abs
import kotlin.math.pow

private const val MESSAGE_SIZE = 8
private const val SIGNAL_REPEATED = 10000
private const val DECIMAL = 10

class FlawedFrequencyTransmission(private val input: List<Int>) {
    private val basePattern = listOf(1, 0, -1, 0)

    fun phases(times: Int): String {
        var output = input
        repeat(times) {
            output = phase(output)
        }
        return firstEightDigits(output)
    }

    private fun phase(input: List<Int>) =
        input.mapIndexed { i, _ ->
            abs(input.drop(i).zip(repeatingPattern(i).take(input.size).toList()).sumOf {
                it.first * it.second
            }) % DECIMAL
        }

    private fun repeatingPattern(i: Int) = sequence {
        while (true) {
            yieldAll(basePattern.flatMap { generateSequence { it }.take(i + 1).toList() })
        }
    }

    fun embedded(times: Int): String {
        val offset =
            input.take(MESSAGE_SIZE - 1).reversed().mapIndexed { i, v -> DECIMAL.toDouble().pow(i) * v }.sum().toInt()
        val value = (offset until SIGNAL_REPEATED * input.size).map { input[it % input.size] }.toMutableList()
        require(offset < SIGNAL_REPEATED * input.size) { "Offset out of bound " }
        require(SIGNAL_REPEATED * input.size <= 2 * offset) { "This code optimisation only works in that case" }
        repeat(times) {
            value.indices.reversed().fold(0) { acc, i ->
                (abs(acc + value[i]) % DECIMAL).also { value[i] = it }
            }
        }
        return firstEightDigits(value)
    }

    private fun firstEightDigits(output: List<Int>) = output.take(MESSAGE_SIZE).joinToString("")
}
