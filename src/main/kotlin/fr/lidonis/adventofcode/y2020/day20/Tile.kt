package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.tail

private val TILE_ID_REGEX = Regex("Tile (\\d+):")

typealias Border = List<Boolean>

data class Tile(val id: Int, val matrix: Matrix<Boolean>) {

    constructor(lines: List<String>) : this(
        lines.head.let { title ->
            TILE_ID_REGEX.matchEntire(title)?.destructured?.component1()?.toInt() ?: error("No tile id found")
        },
        lines.tail.let { content ->
            Matrix(MutableList<ArrayList<Boolean>>(content.size) { ArrayList(content.first().length) }.also {
                for ((i, line) in content.withIndex()) {
                    for ((j, c) in line.withIndex()) {
                        it[i].add(j, c == '#')
                    }
                }
            })
        })

    val borders: List<Border> by lazy {
        listOf(
            top, right, bottom, left,
            top.reversed(), right.reversed(), bottom.reversed(), left.reversed(),
        )
    }

    val orientations: List<Tile> by lazy {
        matrix.allOrientations().map { Tile(id, it) }
    }

    val top by lazy { matrix.head }
    val right by lazy { matrix.map { it.last() } }
    val bottom by lazy { matrix.last() }
    val left by lazy { matrix.map { it.head } }

    fun isMatching(otherTile: Tile) = match(otherTile).isNotEmpty()

    private fun match(otherTile: Tile) = borders intersect otherTile.borders

    fun inner(): Tile {
        return Tile(id, Matrix(matrix.tail.dropLast(1).map { it.tail.dropLast(1) }))
    }
}
