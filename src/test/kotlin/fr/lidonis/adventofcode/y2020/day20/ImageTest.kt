package fr.lidonis.adventofcode.y2020.day20

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ImageTest {

    private val tiles = INPUT.split("\n\n").map { Tile(it.lines()) }

    @Test
    fun display() {
       Image(tiles).buildImage()
    }
}
