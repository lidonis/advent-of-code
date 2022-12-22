package fr.lidonis.adventofcode.y2022.day20

import java.util.LinkedList

private const val GROVES_COORDINATE_1 = 1000
private const val GROVES_COORDINATE_2 = 2000
private const val GROVES_COORDINATE_3 = 3000

private val grovesCoordinates = listOf(GROVES_COORDINATE_1, GROVES_COORDINATE_2, GROVES_COORDINATE_3)

private const val MULTIPLIER = 811589153L
private const val NB_MIX = 10

class GrovePositioningSystem(lines: List<String>) {
    private val elements = lines.map { Element(it.toInt()) }

    fun sum() = LinkedList(elements)
        .run {
            mix()
            sum()
        }

    fun sumDecrypted() = LinkedList(elements).run {
        repeat(NB_MIX) { mix(MULTIPLIER) }
        MULTIPLIER * sum()
    }

    private class Element(val value: Int)

    private fun LinkedList<Element>.mix(multiplier: Long = 1L) {
        for (node in elements) {
            val currentIndex = indexOf(node)
            val newIndex = (currentIndex + node.value * multiplier).mod(lastIndex)
            removeAt(currentIndex)
            add(newIndex, node)
        }
    }

    private fun List<Element>.sum() = indexOfFirst { it.value == 0 }.let { indexOf0 ->
        grovesCoordinates.sumOf { this[(indexOf0 + it) % size].value }
    }
}
