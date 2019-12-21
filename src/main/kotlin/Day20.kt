fun main() {
    val input = InputReader("day20.txt").text
    val maze = SpaceWarpingMaze(input)
    println(maze.shortestPathDonut())
    println(maze.shortestPathInception())
}

class SpaceWarpingMaze(input: String) {

    private val mazeMap = input
        .split("\n")
        .mapIndexed { j, s ->
            s.mapIndexedNotNull { i, c ->
                Position(i, j) to c
            }
        }.flatten()
        .toMap()

    private val openPassages = mazeMap.filterValues { it == '.' }.keys.toMutableList()
    private val portals = searchPortals()
    private val start = portals.first { it.label == "AA" }.exit
    private val end = portals.first { it.label == "ZZ" }.exit

    fun shortestPathDonut(): Int? {
        var steps = 0
        var neighbours = setOf(start)
        val visited = mutableSetOf<Position>()
        var found = false
        while (visited.addAll(neighbours) && !found) {
            if (neighbours.any { it == end }) {
                found = true
                break
            }
            steps++
            neighbours = neighbours.map { getNeighbours(it, visited) }.flatten().toSet()
            println("$steps ${visited.size}")
        }
        return if (found) steps else null
    }

    private fun getNeighbours(position: Position, visited: Set<Position>): List<Position> =
        position.neighbours().filter { !visited.contains(it) && openPassages.contains(it) }
            .map { searchExitPortal(it)?.exit ?: it }

    private fun searchExitPortal(position: Position) =
        portals.firstOrNull { it.entry == position }?.let {
            portals.firstOrNull { p ->
                it.label == p.label && it != p
            }
        }


    private fun searchPortals() =
        mazeMap.map { entry ->
            CardinalPoint.values().mapNotNull { cardinalPoint ->
                val firstMove = entry.key.move(cardinalPoint)
                val second = mazeMap.getOrDefault(firstMove, ' ')
                val secondMove = firstMove.move(cardinalPoint)
                val third = mazeMap[secondMove]
                if (second.isLetter() && third == '.') {
                    openPassages.add(firstMove)
                    when (cardinalPoint) {
                        CardinalPoint.SOUTH, CardinalPoint.EAST -> Portal(
                            entry.value.toString() + second,
                            firstMove,
                            secondMove,
                            isOuter(entry.key)
                        )
                        else -> Portal(second + entry.value.toString(), firstMove, secondMove, isOuter(entry.key))
                    }
                } else {
                    null
                }
            }
        }.flatten()

    private fun isOuter(position: Position) = position.neighbours().any { mazeMap[it] == null }

    fun shortestPathInception(): Int? {
        var steps = 0
        var neighbours = setOf(Node(start))
        val visited = mutableSetOf<Node>()
        var found = false
        val recursiveEnd = Node(end)
        while (visited.addAll(neighbours) && !found) {
            if (neighbours.any { it == recursiveEnd }) {
                found = true
                break
            }
            steps++
            neighbours = neighbours.map { getNeighboursRecursive(it, visited) }.flatten().toSet()
            println("$steps ${visited.size}")
        }
        return if (found) steps else null
    }

    private fun getNeighboursRecursive(currentNode: Node, visited: Set<Node>): List<Node> =
        currentNode.position.neighbours()
            .filter { !visited.contains(Node(it, currentNode.level)) && openPassages.contains(it) }
            .mapNotNull {
                val exitPortal = searchExitPortal(it)
                when {
                    exitPortal == null -> Node(it, currentNode.level)
                    exitPortal.inner && currentNode.level == 0 -> null
                    exitPortal.inner -> Node(exitPortal.exit, currentNode.level - 1)
                    exitPortal.outer -> Node(exitPortal.exit, currentNode.level + 1)
                    else -> null
                }
            }

    data class Portal(val label: String, val entry: Position, val exit: Position, val outer: Boolean) {
        val inner: Boolean
            get() = !outer
    }

    data class Node(val position: Position, val level: Int = 0)
}