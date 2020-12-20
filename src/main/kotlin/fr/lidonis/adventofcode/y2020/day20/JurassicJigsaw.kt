package fr.lidonis.adventofcode.y2020.day20

class JurassicJigsaw(text: String) {

    private val tiles = text.split("\n\n").map { Tile(it.lines()) }

    fun getCornerTiles() = tiles.filter(::isCornerTile).toSet()

    private fun isCornerTile(tile: Tile) = (tiles - tile).filter { it.match(tile) }.size == 2

    private fun waterRoughness(): Int {
        return 0
    }

}