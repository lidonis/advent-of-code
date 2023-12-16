package fr.lidonis.adventofcode.y2019.day24

import fr.lidonis.adventofcode.common.tail

data class RecursiveEris(private val bugs: Set<RecursivePosition>) {

    constructor(input: String) : this(read(input, 0))

    constructor(input: List<String>) : this(
        input.flatMap {
            val z = Regex("Depth (.*):").matchEntire(it.lineSequence().first())?.destructured?.component1()?.toInt()
                ?: error(" No depth found")
            read(it.lines().tail.joinToString("\n"), z)
        }.toSet()
    )

    fun evolve(i: Int) = evolutions().elementAt(i)

    private fun evolutions() = generateSequence(bugs) { current ->
        val activated = current.fold(mutableMapOf<RecursivePosition, Int>()) { acc, j ->
            j.neighbours().forEach { acc[it] = (acc[it] ?: 0) + 1 }
            acc
        }.filterValues { it in 1..2 }.keys - current
        val stayActive = current.filter {
            (it.neighbours() intersect current).size == 1
        }
        activated + stayActive
    }.map { RecursiveEris(it) }

    fun countBugs() = bugs.size

    companion object {
        private fun read(input: String, z: Int) = sequence {
            for ((i, line) in input.lines().withIndex()) {
                for ((j, c) in line.withIndex()) {
                    if (c == '#') yield(RecursivePosition(i, j, z))
                }
            }
        }.toSet()
    }
}
