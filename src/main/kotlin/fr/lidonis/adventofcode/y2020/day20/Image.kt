package fr.lidonis.adventofcode.y2020.day20

class Image(private val tiles: List<Tile>) {

    fun buildImage(){
        val start = tiles.first(::isCornerTile)
        start.display()
        val matches = (tiles - start).filter { it.isMatching(start) }
        println(matches.map { it.matchId(start) })
        val right = matches.first()
        val matches2 = (tiles - start - right).filter { it.isMatching(right) }
        matches2.forEach(Tile::display)
        println(matches2.map { it.matchId(right) })
        val right2 = matches2[1]
        val matches3 = (tiles - start - right - right2).filter { it.isMatching(right2) }
        matches3.forEach(Tile::display)
        println(matches3.map { it.matchId(right2) })
    }

    private fun isCornerTile(tile: Tile) = (tiles - tile).filter { it.isMatching(tile) }.size == 2
}
