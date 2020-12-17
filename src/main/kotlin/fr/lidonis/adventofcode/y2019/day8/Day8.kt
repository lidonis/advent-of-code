package fr.lidonis.adventofcode.y2019.day8

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.ocr.OCR
import fr.lidonis.adventofcode.y2019.AdventOfCode2019

private const val DAY = 8

object Day8 : AdventOfCode2019(DAY) {

    private const val IMAGE_WIDTH = 25
    private const val IMAGE_HEIGHT = 6

    private val decoder = ImageDecoder(input().text(), IMAGE_WIDTH, IMAGE_HEIGHT)

    override fun part1() = decoder.checksum()

    override fun part2() = OCR.detect(PositionSet(decoder.decode().flatMapIndexed { j, s ->
        s.mapIndexedNotNull { i, c -> if (c == '1') Position(i, j) else null }
    }.toSet()))
}
