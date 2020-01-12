package fr.lidonis.adventofcode.y2019.day20

import fr.lidonis.adventofcode.common.Direction
import fr.lidonis.adventofcode.common.Position
import fr.lidonis.adventofcode.common.graph.BreadthFirstSearch

class SpaceWarpingMaze(input: String) {

    private val mazeMap = input
        .lines()
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

    fun shortestPathDonut() = BreadthFirstSearch.search(
        start,
        { it == end },
        ::getNeighbours
    )

    private fun getNeighbours(position: Position) =
        position.neighbours().filter { openPassages.contains(it) }
            .map { searchExitPortal(it)?.exit ?: it }

    private fun searchExitPortal(position: Position) =
        portals.firstOrNull { it.entry == position }?.let {
            portals.firstOrNull { p ->
                it.label == p.label && it != p
            }
        }

    private fun searchPortals() =
        mazeMap.flatMap { entry ->
            Direction.values().mapNotNull { direction ->
                val firstMove = entry.key.move(direction)
                val second = mazeMap.getOrDefault(firstMove, ' ')
                val secondMove = firstMove.move(direction)
                val third = mazeMap[secondMove]
                if (second.isLetter() && third == '.') {
                    openPassages.add(firstMove)
                    when (direction) {
                        Direction.DOWN, Direction.LEFT -> Portal(
                            entry.value.toString() + second,
                            firstMove,
                            secondMove,
                            isOuter(entry.key)
                        )
                        else -> Portal(
                            second + entry.value.toString(),
                            firstMove,
                            secondMove,
                            isOuter(entry.key)
                        )
                    }
                } else {
                    null
                }
            }
        }

    private fun isOuter(position: Position) = position.neighbours().any { mazeMap[it] == null }

    fun shortestPathInception(): Int? {
        val nodeStart = Node(start)
        val endNode = Node(end)
        return BreadthFirstSearch.search(
            nodeStart,
            { it == endNode },
            ::getNeighboursInception
        )
    }


    private fun getNeighboursInception(currentNode: Node) =
        currentNode.position.neighbours()
            .filter { openPassages.contains(it) }
            .mapNotNull {
                val exitPortal = searchExitPortal(it)
                when {
                    exitPortal == null -> Node(
                        it,
                        currentNode.level
                    )
                    exitPortal.inner && currentNode.level == 0 -> null
                    exitPortal.inner -> Node(
                        exitPortal.exit,
                        currentNode.level - 1
                    )
                    exitPortal.outer -> Node(
                        exitPortal.exit,
                        currentNode.level + 1
                    )
                    else -> null
                }
            }

    data class Portal(val label: String, val entry: Position, val exit: Position, val outer: Boolean) {
        val inner: Boolean
            get() = !outer
    }

    data class Node(val position: Position, val level: Int = 0)
}