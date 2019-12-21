object BreadthFirstSearch {

    fun <T> search(
        start: T,
        isFound: (T) -> Boolean,
        getNeighbours: (T, Set<T>) -> List<T>
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
            neighbours = neighbours.map { getNeighbours(it, visited) }.flatten().toSet()
            println("$steps ${visited.size}")
        }
        return if (found) steps else null
    }
}