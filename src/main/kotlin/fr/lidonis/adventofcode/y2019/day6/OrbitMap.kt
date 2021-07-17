package fr.lidonis.adventofcode.y2019.day6

import fr.lidonis.adventofcode.common.graph.BreadthFirstSearch

class OrbitMap(input: String) {

    private val orbits = mutableMapOf<String, MutableSet<String>>()
    private val orbitCount = mutableMapOf<String, Int>()
    private val neighbours = mutableMapOf<String, Set<String>>()

    init {
        input.lines().forEach {
            it.split(")").run {
                orbits.getOrPut(this[1]) { mutableSetOf() }.add(this[0])
            }
        }
    }

    fun countTotalOrbits() = orbits.values.sumOf(::countOrbits)
    private fun countOrbits(orbits: Set<String>): Int {
        return orbits.sumOf { 1 + countOrbit(it) }
    }

    private fun countOrbit(key: String): Int =
        orbitCount.getOrPut(key) { orbits[key]?.sumOf { 1 + countOrbit(it) } ?: 0 }

    fun minimumOrbitalTransfers(start: String, end: String) = orbitalTransfer(start, end) - 2

    private fun orbitalTransfer(start: String, end: String) =
        BreadthFirstSearch.search(start, { it == end }, ::findNeighbours) ?: error("Can't find orbital transfer")

    private fun findNeighbours(node: String) =
        neighbours.getOrPut(node) { orbits.filterValues { node in it }.keys + (orbits[node] ?: emptySet()) }
}
