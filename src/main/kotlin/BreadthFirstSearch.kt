object BreadthFirstSearch {

    fun <T> search(
        start: T,
        isFound: (T) -> Boolean,
        findNeighbours: (T, Set<T>) -> List<T>
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
            neighbours = neighbours.flatMap { findNeighbours(it, visited) }.toSet()
        }
        return if (found) steps else null
    }
}