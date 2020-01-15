package fr.lidonis.adventofcode.common.ocr

import fr.lidonis.adventofcode.common.InputReader
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import java.io.File

object OCR {

    private const val LETTER_WIDTH = 4

    private val mappings = File(OCR::class.java.getResource("/ocr/").file).run {
        listFiles()?.map { score(InputReader("/ocr/${it.name}")) to it.name[0] }?.toMap()
            ?: error("OCR init failed")
    }

    private fun score(inputReader: InputReader) = PositionSet(
        inputReader.lines().mapIndexed { j, s ->
            s.mapIndexedNotNull { i, c ->
                if (c == '█') Position(i, j) else null
            }
        }.flatten().toSet()
    ).score

    fun detect(set: PositionSet) = set.chunked(OCR.LETTER_WIDTH + 1).map(::detectChar).joinToString("")
    private fun detectChar(set: PositionSet) = mappings[set.moveTo(Position.ORIGIN).score] ?: ' '
}
