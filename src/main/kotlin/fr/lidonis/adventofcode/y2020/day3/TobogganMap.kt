package fr.lidonis.adventofcode.y2020.day3

class TobogganMap(map: List<String>) {

    data class Slope(val right: Int, val down: Int)

    private val trees = sequence {
        for (line in map) {
            yield(sequence {
                for ((i, c) in line.withIndex()) {
                    if (c == '#') yield(i)
                }
            }.toList())
        }
    }.toList()
    private val size = map.first().length

    fun treeEncounter(slope: Slope) =
        (trees.indices step slope.down)
            .count { (it * slope.right / slope.down) % size in trees[it] }
}
