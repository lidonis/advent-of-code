package fr.lidonis.adventofcode.y2021.day15

import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.graph.AStar

private const val EXTEND_SIZE = 5
private const val MAX_RISK_LEVEL = 9

class Chiton(lines: List<String>) {

    private val mapPart1 = lines.parse()

    fun part1() = searchPaths(mapPart1)

    private fun searchPaths(map: Map<Position, Int>): Int {
        val boundingBox = PositionSet(map.keys).boundingBox
        return listOf(Direction.RIGHT, Direction.UP).minOfOrNull {
            searchPath(map, boundingBox, it).getScore()
        } ?: error("No path found")
    }

    private fun searchPath(
        map: Map<Position, Int>,
        boundingBox: PositionSet.BoundingBox,
        direction: Direction,
    ) = AStar.search(
        SubmarinePath(boundingBox.start, direction),
        { (p, _) -> p == boundingBox.end },
        { it.neighbours().filter { (p) -> p in map } },
        { _, (point) -> map[point] ?: 0 }
    )

    private val mapPart2: Map<Position, Int>
        get() {
            val boundingBoxPart1 = PositionSet(mapPart1.keys).boundingBox
            return buildMap {
                for (x in 0 until EXTEND_SIZE) {
                    for (y in 0 until EXTEND_SIZE) {
                        for (p in mapPart1.keys) {
                            put(
                                p + Position(x * (boundingBoxPart1.end.x + 1), y * (boundingBoxPart1.end.y + 1)),
                                ((mapPart1[p] ?: 0) + x + y - 1) % MAX_RISK_LEVEL + 1
                            )
                        }
                    }
                }
            }
        }

    fun part2() = searchPaths(mapPart2)

    private fun List<String>.parse() = flatMapIndexed { y, line -> line.parseLine(y) }.toMap()

    private fun String.parseLine(y: Int) = mapIndexed { x, c -> Position(x, y) to c.digitToInt() }

    private data class SubmarinePath(val position: Position, val direction: Direction) {
        fun neighbours() = buildList {
            add(SubmarinePath(position + direction, direction))
            add(SubmarinePath(position + direction.turnRight(), direction.turnRight()))
            add(SubmarinePath(position + direction.turnLeft(), direction.turnLeft()))
        }
    }
}
