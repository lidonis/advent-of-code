package fr.lidonis.adventofcode.y2021.day7

import kotlin.math.abs
import kotlin.math.roundToInt

class CrabSubmarines(text: String) {

    private val submarines = text.split(",").map(String::toInt)

    fun fuelToAlignConstantBurn(): Int {
        val median = submarines.median()
        return submarines.sumOf { abs(it - median) }
    }

    fun fuelToAlignIncreasingBurn(): Int {
        val average = submarines.average()
        val average0 = average.roundToInt()
        val average1 = if (average0 <= average) average0 + 1 else average0 - 1
        return minOf(
            submarines.sumOf { burn(abs(it - average0)) },
            submarines.sumOf { burn(abs(it - average1)) }
        )
    }

    companion object {
        private fun burn(x: Int): Int = x * (x + 1) / 2
    }
}

fun List<Int>.median() = sorted().let { (it[it.size / 2] + it[(it.size - 1) / 2]) / 2 }
