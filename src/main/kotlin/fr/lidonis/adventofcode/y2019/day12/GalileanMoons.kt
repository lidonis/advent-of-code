package fr.lidonis.adventofcode.y2019.day12

import fr.lidonis.adventofcode.common.math.lcm
import kotlin.math.abs
import kotlin.math.sign

class GalileanMoons(lines: List<String>) {
    private val numPoints = lines.size

    private val axes: List<List<Int>> = mutableListOf<MutableList<Int>>().apply {
        for (line in lines) {
            for ((i, match) in REGEX.findAll(line).withIndex()) {
                getOrElse(i) { mutableListOf<Int>().also { add(it) } }.add(match.value.toInt())
            }
        }
    }

    fun totalEnergy(n: Int = 1000): Int {
        val potentials = MutableList(numPoints) { 0 }
        val kinetics = MutableList(numPoints) { 0 }
        for (axis in axes) {
            for ((i, pair) in simulate(axis).elementAt(n - 1).withIndex()) {
                potentials[i] += abs(pair.first)
                kinetics[i] += abs(pair.second)
            }
        }
        return potentials.zip(kinetics) { a, b -> a * b }.sum()
    }

    fun stepsToRepeatPreviousState(): Long = axes.fold(1L) { acc, axis ->
        lcm(
            acc,
            simulate(axis).indexOfFirst { pairs ->
                pairs.withIndex().all { (i, pair) -> pair.second == 0 && pair.first == axis[i] }
            }.toLong() + 1
        )
    }

    companion object {
        private val REGEX = """-?\d+""".toRegex()

        private fun simulate(start: Iterable<Int>): Sequence<List<Pair<Int, Int>>> = sequence {
            val points = start.toMutableList()
            val velocities = MutableList(points.size) { 0 }
            while (true) {
                for ((i, point) in points.withIndex()) {
                    velocities[i] += points.sumBy { (it - point).sign }
                }
                for ((i, velocity) in velocities.withIndex()) {
                    points[i] += velocity
                }
                yield(points.zip(velocities))
            }
        }
    }
}
