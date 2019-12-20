fun main() {
    val input = InputReader("day20.txt").text
    val maze = SpaceWarpingMaze(input)
    println(maze.shortestPath())
}

class SpaceWarpingMaze(input: String) {

    private val mazeMap = input
        .split("\n")
        .mapIndexed { j, s ->
            s.mapIndexedNotNull() { i, c ->
                Position(i, j) to c
            }
        }.flatten()
        .filter { it.second != ' ' && it.second != '#' }
        .toMap()

    private val openPassages = mazeMap.filterValues { it == '.' }.keys.toMutableList()
    private val portals = searchPortals()
    private val start = portals.first { it.label == "AA" }.exit
    private val end = portals.first { it.label == "ZZ" }.exit

    fun shortestPath(): Int? {
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
            .map { teleport(it) }

    private fun teleport(position: Position) =
        portals.firstOrNull { it.entry == position }?.let {
            portals.firstOrNull { p ->
                it.label == p.label && it != p
            }
        }?.exit ?: position


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
                            secondMove
                        )
                        else -> Portal(second + entry.value.toString(), firstMove, secondMove)
                    }
                } else {
                    null
                }
            }
        }.flatten()

    data class Portal(val label: String, val entry: Position, val exit: Position)
}