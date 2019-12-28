package fr.lidonis.adventofcode.y2019.day6

class OrbitMap(input: String) {

    private val orbitMap = mutableMapOf<String, MutableSet<String>>()

    init {
        input.lines().forEach {
            it.split(")").run {
                orbitMap.getOrPut(this[1]) { mutableSetOf() }.add(this[0])
            }
        }
    }

    fun countTotalOrbits() = orbitMap.values.map(::countOrbits).sum()
    private fun countOrbits(orbits: Set<String>): Int = orbits.map { 1 + countOrbit(it) }.sum()
    private fun countOrbit(key: String): Int = orbitMap[key]?.map { 1 + countOrbit(it) }?.sum() ?: 0

    fun minimumOrbitalTransfers(start: String, end: String) = -2 + (
        BreadthFirstSearch.search(start, { it == end }, ::findNeighbours)?: error("Can't find orbital transfer"))

    private fun findNeighbours(node: String): Set<String> {
        return orbitMap.filterValues { it.contains(node) }.keys + (orbitMap[node] ?: emptySet())
    }
}
