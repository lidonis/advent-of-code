package fr.lidonis.adventofcode.y2023.day17

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.graph.AStar

private const val MAX_BLOCKS = 3
private const val MIN_BLOCKS_ULTRA = 4
private const val MAX_BLOCKS_ULTRA = 10

class ClumsyCrucible(lines: List<String>) {

    private val map = lines.parse()

    private val boundingBox = PositionSet(map.keys).boundingBox

    fun part1() = searchPaths(CruciblePath::neighbours, 0)

    fun part2() = searchPaths(CruciblePath::ultraNeighbours, MIN_BLOCKS_ULTRA)

    private fun searchPaths(
        neighbourFunction: CruciblePath.() -> Iterable<CruciblePath>,
        minMoves: Int,
    ) =
        listOf(Direction.RIGHT, Direction.UP).minOfOrNull {
            searchPath(it, neighbourFunction, minMoves).getScore()
        } ?: error("No path found")

    private fun searchPath(
        direction: Direction,
        neighbourFunction: CruciblePath.() -> Iterable<CruciblePath>,
        minMoves: Int
    ) = AStar.search(
        CruciblePath(boundingBox.start, direction, 0),
        { (p, _, times) -> p == boundingBox.end && times >= minMoves },
        { it.neighbourFunction().filter { (p) -> p in map } },
        { _, (point) -> map[point] ?: 0 }
    )

    private fun List<String>.parse() = flatMapIndexed { y, line -> line.parseLine(y) }.toMap()

    private fun String.parseLine(y: Int) = mapIndexed { x, c -> Position(x, y) to c.digitToInt() }

    data class CruciblePath(val position: Position, val direction: Direction, val times: Int) {
        fun neighbours() = buildList {
            if (times < MAX_BLOCKS) {
                add(CruciblePath(position + direction, direction, times + 1))
            }
            add(CruciblePath(position + direction.turnRight(), direction.turnRight(), 1))
            add(CruciblePath(position + direction.turnLeft(), direction.turnLeft(), 1))
        }

        fun ultraNeighbours() = buildList {
            if (times < MAX_BLOCKS_ULTRA) {
                add(CruciblePath(position + direction, direction, times + 1))
            }
            if (times >= MIN_BLOCKS_ULTRA) {
                add(CruciblePath(position + direction.turnRight(), direction.turnRight(), 1))
                add(CruciblePath(position + direction.turnLeft(), direction.turnLeft(), 1))
            }
        }
    }
}
