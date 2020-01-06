package fr.lidonis.adventofcode.common.graph

object BreadthFirstSearch {

    fun <T> search(
        start: T,
        isFound: (T) -> Boolean,
        findNeighbours: (T) -> Iterable<T>
    ): Int? {
        var steps = 0
        var neighbours = setOf(start)
        val visited = mutableSetOf<T>()
        var found = false
        while (visited.addAll(neighbours) && !found) {
            if (neighbours.any(isFound)) {
                found = true
                break
            }
            steps++
            neighbours = neighbours.flatMap { findNeighbours(it) }.toSet().subtract(visited)
        }
        return if (found) steps else null
    }
}