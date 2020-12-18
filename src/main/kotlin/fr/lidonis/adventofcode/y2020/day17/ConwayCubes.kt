package fr.lidonis.adventofcode.y2020.day17

private const val ACTIVATED_COUNT = 3
private const val STAY_ACTIVE_MIN = 2
private const val STAY_ACTIVE_MAX = 3

data class ConwayCubes(private val lines: List<String>) {

    fun cube(cycleCount: Int): Int {
        return boot(readLines { i, j -> ConwayCube(i, j, 0) }).elementAt(cycleCount).size
    }

    private fun <T> readLines(constructor: (Int, Int) -> Coordinate<T>) = sequence {
        for ((i, line) in lines.withIndex()) {
            for ((j, c) in line.withIndex()) {
                if (c == '#') yield(constructor(i, j))
            }
        }
    }.toSet()

    fun hypercube(cycleCount: Int) =
        boot(readLines { i, j -> ConwayHyperCube(i, j, 0, 0) }).elementAt(cycleCount).size

    private fun <R : Coordinate<R>> boot(coordinates: Set<Coordinate<R>>) =
        generateSequence(coordinates) { current ->
            val activated = current.fold(mutableMapOf<Coordinate<R>, Int>()) { acc, j ->
                j.adjacentCoordinates().forEach { acc[it] = (acc[it] ?: 0) + 1 }
                acc
            }.filterValues { it == ACTIVATED_COUNT }.keys
            val stayActive = current.filter {
                (it.adjacentCoordinates() intersect current).size in STAY_ACTIVE_MIN..STAY_ACTIVE_MAX
            }
            activated + stayActive
        }

    interface Coordinate<T> {
        fun adjacentCoordinates(): Set<T>
    }

    data class ConwayCube(val x: Int, val y: Int, val z: Int) : Coordinate<ConwayCube> {
        override fun adjacentCoordinates() = sequence {
            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        yield(ConwayCube(x + i, y + j, z + k))
                    }
                }
            }
        }.toSet().minus(this)
    }

    data class ConwayHyperCube(val x: Int, val y: Int, val z: Int, val w: Int) : Coordinate<ConwayHyperCube> {
        override fun adjacentCoordinates() = sequence {
            for (i in -1..1) {
                for (j in -1..1) {
                    for (k in -1..1) {
                        for (l in -1..1) {
                            yield(ConwayHyperCube(x + i, y + j, z + k, w + l))
                        }
                    }
                }
            }
        }.toSet().minus(this)
    }
}
