package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.math.pow

const val SIZE = 5

data class Eris(private val bugs: Set<Position>) {

    constructor(input: String) : this(
        sequence {
            for ((i, line) in input.lines().withIndex()) {
                for ((j, c) in line.withIndex()) {
                    if (c == '#') yield(Position(j, i))
                }
            }
        }.toSet()
    )

    val biodiversityRating by lazy {
        bugs.sumOf { 2 pow (it.x + it.y * SIZE) }
    }

    fun evolve() = evolutions().elementAt(1)

    fun evolutions() = generateSequence(bugs) { current ->
        val activated = current.fold(mutableMapOf<Position, Int>()) { acc, j ->
            neighbours(j).forEach { acc[it] = (acc[it] ?: 0) + 1 }
            acc
        }.filterValues { it in 1..2 }.keys - current
        val stayActive = current.filter {
            (neighbours(it) intersect current).size == 1
        }
        activated + stayActive
    }.map { Eris(it) }

    private fun neighbours(center: Position) = center.neighbours().filter { position ->
        position.x in 0 until SIZE && position.y in 0 until SIZE
    }
}
