package fr.lidonis.adventofcode.common.ocr

import fr.lidonis.adventofcode.common.InputReader
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import java.io.File

object OCR {

    private const val LETTER_WIDTH = 4

    private val mappings = OCR::class.java.getResource("/ocr/")?.file?.run {
        File(this).run {
            listFiles()?.associate { score(InputReader("/ocr/${it.name}")) to it.name[0] }
                ?: error("OCR init failed")
        }
    } ?: error("Directory not found")

    private fun score(inputReader: InputReader) = PositionSet(
        sequence {
            for ((i, s) in inputReader.lines().withIndex()) {
                for ((j, c) in s.withIndex()) {
                    if (c == '█') yield(Position(j, i))
                }
            }
        }.toSet()
    ).score

    fun detect(set: PositionSet) = set.chunkedX(LETTER_WIDTH + 1).map(::detectChar).joinToString("")
    private fun detectChar(set: PositionSet) = mappings[set.moveTo(Position.ORIGIN).score] ?: ' '
}
