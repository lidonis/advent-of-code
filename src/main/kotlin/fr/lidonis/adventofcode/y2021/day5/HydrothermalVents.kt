package fr.lidonis.adventofcode.y2021.day5

import fr.lidonis.adventofcode.common.geo.plane.Line
import fr.lidonis.adventofcode.common.geo.plane.Position

private val LINE_REGEX = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()

class HydrothermalVents(inputs: List<String>) {

    private val lines = inputs.map { input ->
        val (x1, y1, x2, y2) = LINE_REGEX.find(input)?.destructured ?: error("wrong format")
        Line(Position(x1.toInt(), y1.toInt()), Position(x2.toInt(), y2.toInt()))
    }

    fun countAtLeastTwoLinesOverlapWithHorizontalOrVertical(): Int = lines
        .filter { it.isHorizontal || it.isVertical }
        .countOverlap()

    fun countAtLeastTwoLinesOverlap(): Int = lines.countOverlap()

    private fun List<Line>.countOverlap() = map(Line::allPositions)
        .flatten()
        .groupingBy { it }
        .eachCount()
        .count { it.value >= 2 }
}
