package fr.lidonis.adventofcode.y2021.day13

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.ocr.OCR
import fr.lidonis.adventofcode.y2021.day13.FoldAxis.X
import fr.lidonis.adventofcode.y2021.day13.FoldAxis.Y

private const val FOLD_INSTRUCTION_REGEX = """fold along ([x|y])=(\d+)"""

class TransparentOrigami(lines: List<String>) {

    private val dots = lines.asSequence()
        .filter { it.isNotEmpty() && it[0].isDigit() }
        .map { it.split(",") }
        .map { (x, y) -> Position(x.toInt(), y.toInt()) }

    private val foldInstructions = lines.asSequence()
        .filter { it.startsWith("fold along") }
        .mapNotNull { FOLD_INSTRUCTION_REGEX.toRegex().matchEntire(it)?.destructured }
        .map { (axis, index) -> FoldInstruction(FoldAxis.fromString(axis), index.toInt()) }

    fun countVisibleDotsAfter1Fold() = dots.fold(foldInstructions.first()).distinct().count()

    fun display() = OCR.detect(
        PositionSet(
            foldInstructions
                .fold(dots, Sequence<Position>::fold).toSet()
        )
    )
}

private fun Sequence<Position>.fold(instruction: FoldInstruction) = map(instruction::fold)

private data class FoldInstruction(val axis: FoldAxis, val index: Int) {

    fun fold(position: Position) = when (axis) {
        X -> position.foldX(index)
        Y -> position.foldY(index)
    }

    private fun Position.foldY(yAxis: Int) = when {
        y < yAxis -> this
        y > yAxis -> Position(x, 2 * yAxis - y)
        else -> error("Fold on axis")
    }

    private fun Position.foldX(xAxis: Int) = when {
        x < xAxis -> this
        x > xAxis -> Position(2 * xAxis - x, y)
        else -> error("Fold on axis")
    }
}

private enum class FoldAxis {
    X, Y;

    companion object {
        fun fromString(axis: String) = when (axis) {
            "x" -> X
            "y" -> Y
            else -> error("Unknown axis")
        }
    }
}
