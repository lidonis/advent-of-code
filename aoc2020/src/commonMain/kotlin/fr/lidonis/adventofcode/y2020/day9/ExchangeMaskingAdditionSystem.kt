package fr.lidonis.adventofcode.y2020.day9

import fr.lidonis.adventofcode.common.combine

class ExchangeMaskingAdditionSystem(private val numbers: List<Long>, private val preambleSize: Int) {

    private val findInvalidNumber: Long? by lazy {
        numbers.windowed(preambleSize + 1).firstOrNull {
            !isSumOfPrevious(it.take(preambleSize), it.last())
        }?.last()
    }

    fun findInvalidNumber() = findInvalidNumber

    private fun isSumOfPrevious(previous: List<Long>, n: Long): Boolean {
        return previous.combine(2).any { it.sum() == n }
    }

    fun findWeakness(): Long? = findInvalidNumber()?.let { invalidNumber ->
        generateSequence(2, Int::inc).mapNotNull { size ->
            findContiguousSet(size, invalidNumber)
        }.firstOrNull()?.let(this::sumMinMax)
    }

    private fun findContiguousSet(size: Int, invalidNumber: Long) =
        numbers.windowed(size).firstOrNull { it.sum() == invalidNumber }

    private fun sumMinMax(it: List<Long>) = it.minOrNull()!! + it.maxOrNull()!!
}
