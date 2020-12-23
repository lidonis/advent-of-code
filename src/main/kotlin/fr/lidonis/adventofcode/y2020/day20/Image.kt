package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.math.Matrix


private val SEA_MONSTER = """
                                  #
            #    ##    ##    ###   
             #  #  #  #  #  #      
""".trimIndent().lines()

class Image(tiles: List<Tile>) {

    private val borderTiles = getBorderTiles(tiles)

    fun buildImage(): Int {
        val corners = findCorners(borderTiles)
        val topLeft = corners.head
        val topLeftBorders = getTileEdgeBorders(borderTiles)[topLeft] ?: error("")
        val topLeftOriented = topLeft.orientations.find { tile ->
            topLeftBorders.contains(tile.top) && topLeftBorders.contains(tile.left)
        } ?: error("")
        val solved = solveBottomAndRight(topLeftOriented)
        val borderRemoved = solved.map { tiles -> tiles.map { tile -> tile.inner() } }

        val positions = sequence {
            for ((i, tileRow) in borderRemoved.withIndex()) {
                for ((j, tile) in tileRow.withIndex()) {
                    for (x in 0 until tile.matrix.row) {
                        for (y in 0 until tile.matrix.column) {
                            if (tile.matrix[x][y]) yield(Position(i * 8 + x, j * 8 + y))
                        }
                    }
                }
            }
        }.toSet()

        return positions.size
    }


    private fun getBorderTiles(tiles: List<Tile>) = tiles.flatMap { tile -> tile.borders.map { it to tile } }
        .groupBy({ it.first }, { it.second })

    private fun findCorners(borderTiles: Map<Border, List<Tile>>): List<Tile> {
        return getTileEdgeBorders(borderTiles)
            .filter { it.value.size == 2 * 2 }
            .keys
            .toList()
    }

    private fun getTileEdgeBorders(borderTiles: Map<Border, List<Tile>>): Map<Tile, Set<Border>> {
        val edgeBorderTiles = borderTiles.filterValues { it.size == 1 }.mapValues { it.value.first() }
        return edgeBorderTiles.entries.groupBy { it.value }
            .mapValues { it.value.fold(setOf()) { acc, entry -> setOf(entry.key) + acc } }
    }

    private fun solveBottomAndRight(topLeft: Tile): Matrix<Tile> {
        return Matrix(listOf(solveRight(topLeft)) + solveBottom(topLeft).values)
    }

    private fun solveRight(tile: Tile) = listOf(tile) + solveRow(tile)

    private fun solveRow(tileLeft: Tile): List<Tile> {
        val border = tileLeft.right
        val newTiles = borderTiles[border]?.toSet()?.filterNot { it.id == tileLeft.id } ?: error("")
        return when {
            newTiles.size == 1 -> {
                val newTile = newTiles.head
                val newTileOriented = newTile.orientations.find { it.left == border } ?: error("")
                solveRight(newTileOriented)
            }
            newTiles.isEmpty() -> emptyList()
            else -> error("ambiguous new tile")
        }
    }

    private fun solveBottom(tileTop: Tile): Matrix<Tile> {
        val border = tileTop.bottom
        val newTiles = borderTiles[border]?.toSet()?.filterNot { it.id == tileTop.id } ?: error("")
        return when {
            newTiles.size == 1 -> {
                val newTile = newTiles.head
                val newTileOriented = newTile.orientations.find { it.top == border } ?: error("")
                solveBottomAndRight(newTileOriented)
            }
            newTiles.isEmpty() -> Matrix(emptyList())
            else -> error("ambiguous new tile")
        }
    }
}
