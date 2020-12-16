package fr.lidonis.adventofcode.y2020.day3

class TobogganMap(map: List<String>) {

    data class Slope(val right: Int, val down: Int)

    private val trees = map.map { line -> line.mapIndexedNotNull { i, char -> if (char == '#') i else null } }
    private val size = map.first().length

    fun treeEncounter(slope: Slope) =
        (trees.indices step slope.down).count { trees[it].contains((it * slope.right / slope.down) % size) }

}
