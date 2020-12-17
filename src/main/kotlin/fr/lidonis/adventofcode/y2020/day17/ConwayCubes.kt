package fr.lidonis.adventofcode.y2020.day17

private const val ACTIVATED_COUNT = 3
private const val STAY_ACTIVE_MIN = 2
private const val STAY_ACTIVE_MAX = 3

data class ConwayCubes(private val lines: List<String>) {

    fun cube(cycleCount: Int): Int {
        val coordinates =
            lines.flatMapIndexed { i, s ->
                s.mapIndexed { j, c -> if (c == '#') ConwayCube(i, j, 0) else null }
            }.filterNotNull().toSet()
        return boot(coordinates).elementAt(cycleCount).size
    }

    fun hypercube(cycleCount: Int): Int {
        val coordinates =
            lines.flatMapIndexed { i, s ->
                s.mapIndexed { j, c -> if (c == '#') ConwayHyperCube(i, j, 0, 0) else null }
            }.filterNotNull().toSet()
        return boot(coordinates).elementAt(cycleCount).size
    }

    private fun <R : Coordinate<R>> boot(coordinates: Set<Coordinate<R>>): Sequence<Set<Coordinate<R>>> {
        return generateSequence(coordinates) { current ->
            val activated = current.fold(mutableMapOf<Coordinate<R>, Int>()) { acc, j ->
                j.adjacentCoordinates().forEach { acc[it] = (acc[it] ?: 0) + 1 }
                acc
            }.filterValues { it == ACTIVATED_COUNT }.keys
            val stayActive = current.filter {
                (it.adjacentCoordinates() intersect current).size in STAY_ACTIVE_MIN..STAY_ACTIVE_MAX
            }
            activated + stayActive
        }
    }

    interface Coordinate<T> {
        fun adjacentCoordinates(): Set<T>
    }

    data class ConwayCube(val x: Int, val y: Int, val z: Int) : Coordinate<ConwayCube> {
        override fun adjacentCoordinates() = (-1..1).flatMap { i ->
            (-1..1).flatMap { j ->
                (-1..1).map { k ->
                    ConwayCube(x + i, y + j, z + k)
                }
            }
        }.minus(this).toSet()
    }

    data class ConwayHyperCube(val x: Int, val y: Int, val z: Int, val w: Int) : Coordinate<ConwayHyperCube> {
        override fun adjacentCoordinates() = (-1..1).flatMap { i ->
            (-1..1).flatMap { j ->
                (-1..1).flatMap { k ->
                    (-1..1).map { l -> ConwayHyperCube(x + i, y + j, z + k, w + l) }
                }
            }
        }.minus(this).toSet()
    }
}
