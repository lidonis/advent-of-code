package fr.lidonis.adventofcode.y2022.day12

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.graph.BreadthFirstSearch

private const val START_VALUE = 0
private const val A_VALUE = 1
private const val EXIT_VALUE = 27

class HillClimbingAlgorithm(lines: List<String>) {

    private val heightMap = lines
        .flatMapIndexed { j, s ->
            s.mapIndexed { i, c ->
                Position(i, j) to when (c) {
                    'S' -> START_VALUE
                    'E' -> EXIT_VALUE
                    else -> c.code - 'a'.code + A_VALUE
                }
            }
        }.toMap()

    private fun height(position: Position) = heightMap[position] ?: error("out of map")

    fun fewestStepsFromStart() = search(
        START_VALUE,
        EXIT_VALUE,
        ::findNeighboursUp
    )

    fun fewestStepsFromA() = search(
        EXIT_VALUE,
        A_VALUE,
        ::findNeighboursDown
    )

    private fun search(startValue: Int, endValue: Int, neighbours: (Position) -> Iterable<Position>) =
        BreadthFirstSearch.search(
            heightMap.filterValues { it == startValue }.keys.single(),
            { p -> heightMap[p] == endValue },
            neighbours
        )

    private fun findNeighboursUp(position: Position) = findNeighbours(position) {
        it <= height(position) + 1
    }

    private fun findNeighboursDown(position: Position) = findNeighbours(position) {
        it >=  height(position) - 1
    }

    private fun findNeighbours(position: Position, canClimb: (Int) -> Boolean) =
        position.neighbours().associateWith { heightMap[it] }
            .filter { entry -> entry.value?.let(canClimb) ?: false }
            .map { it.key }

}
