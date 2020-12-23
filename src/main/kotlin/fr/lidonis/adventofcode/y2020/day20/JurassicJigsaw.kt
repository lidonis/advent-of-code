package fr.lidonis.adventofcode.y2020.day20

class JurassicJigsaw(text: String) {

    private val tiles = text.split("\n\n").map { Tile(it.lines()) }

    fun getCornerTiles() = tiles.filter(::isCornerTile).toSet()

    private fun isCornerTile(tile: Tile) = (tiles - tile).filter { it.isMatching(tile) }.size == 2

    fun waterRoughness(): Int {
        return Image(tiles).buildImage()
    }

}
