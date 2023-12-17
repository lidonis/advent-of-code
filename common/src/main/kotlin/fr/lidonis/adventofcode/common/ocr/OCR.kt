package fr.lidonis.adventofcode.common.ocr

import fr.lidonis.adventofcode.common.ResourceReader
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

object OCR {

    private const val LETTER_WIDTH = 4

    private val mappings = ('A'..'Z').associateBy {
        ResourceReader("/ocr/$it.txt")?.lines()?.let(::score)
    }

    private fun score(lines: List<String>) = PositionSet(
        sequence {
            for ((i, s) in lines.withIndex()) {
                for ((j, c) in s.withIndex()) {
                    if (c == '█') yield(Position(j, i))
                }
            }
        }.toSet()
    ).score

    fun detect(set: PositionSet) = set.chunked(LETTER_WIDTH + 1).map(::detectChar).joinToString("")
    private fun detectChar(set: PositionSet) = mappings[set.moveTo(Position.ORIGIN).score] ?: ' '
}
