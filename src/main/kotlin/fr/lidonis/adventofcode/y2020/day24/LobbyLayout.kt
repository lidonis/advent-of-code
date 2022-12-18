package fr.lidonis.adventofcode.y2020.day24

private const val ACTIVATED_COUNT = 2
private const val SURVIVE_COUNT = 1

class LobbyLayout(private val tiles: Set<Coordinate>) {

    fun countBlackTiles(): Int {
        return tiles.size
    }

    fun evolve(nbEvolution: Int) = LobbyLayout(generateSequence(tiles) { current ->
        val activated = current.fold(mutableMapOf<Coordinate, Int>()) { acc, j ->
            j.neighbours().forEach { acc[it] = (acc[it] ?: 0) + 1 }
            acc
        }.filterValues { it == ACTIVATED_COUNT }.keys
        val stayActive = current.filter {
            (it.neighbours() intersect current).size == SURVIVE_COUNT
        }
        activated + stayActive
    }.elementAt(nbEvolution))

    companion object {
        fun fromLines(lines: List<String>) = LobbyLayout(lines.fold(mutableSetOf()) { acc, line ->
            val coordinate = Coordinate.fromDirections(line)
            if (coordinate in acc) acc.remove(coordinate) else acc.add(coordinate)
            acc
        })
    }
}
