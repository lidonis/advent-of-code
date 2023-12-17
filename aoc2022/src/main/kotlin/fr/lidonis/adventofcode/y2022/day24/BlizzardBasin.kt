package fr.lidonis.adventofcode.y2022.day24

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.graph.BreadthFirstSearch

class BlizzardBasin(input: String) {
    private val blizzards = Blizzards.parse(input)
    private val blizzardsSteps = mutableMapOf<Int, Blizzards>()
    private val blizzardsStepsSets = mutableMapOf<Int, Set<Position>>()

    private val start = Position(1, 0)
    private val end = Position(blizzards.column - 2, blizzards.row - 1)

    private val boundingBox = PositionSet.BoundingBox(
        Position(1, 1),
        Position(blizzards.column - 2, blizzards.row - 2)
    )

    val go by lazy { shortestPath() }

    private fun shortestPath(
        start: Position = this.start,
        end: Position = this.end,
        step: Int = 0,
    ) = BreadthFirstSearch.search(start to step, { it.first == end }) { state ->
        (state.first.neighbours() + state.first)
            .filter { pos -> allowed(pos, state.second) }
            .map { it to state.second + 1 }
    } ?: error("end not found")

    fun shortestPathBackAndForth(): Any {
        val back = shortestPath(end, start, go)
        val forth = shortestPath(step = go + back)
        return go + back + forth
    }

    private fun blizzards(step: Int) =
        blizzardsStepsSets.computeIfAbsent(step) {
            blizzardsSteps.computeIfAbsent(step) {
                blizzardsSteps.getOrDefault(it - 1, blizzards).evolve(1)
            }.positions.map { it.first }.toSet()
        }

    private fun allowed(position: Position, step: Int): Boolean {
        if (position == start || position == end) return true
        return position in boundingBox && position !in blizzards(step)
    }
}
