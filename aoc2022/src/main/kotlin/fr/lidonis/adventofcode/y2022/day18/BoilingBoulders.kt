package fr.lidonis.adventofcode.y2022.day18

import kotlin.reflect.KProperty1

class BoilingBoulders(lines: List<String>) {

    private val scan =
        lines
            .asSequence()
            .map { it.split(",") }
            .map { it.map(String::toInt) }
            .map { (x, y, z) -> Cube(x, y, z) }
            .toSet()

    fun surfaceArea() = scan.sumOf { cube ->
        cube.neighbours().count { it !in scan }
    }

    fun exteriorSurfaceArea(): Any {
        val (minX, maxX) = minMax(Cube::x)
        val (minY, maxY) = minMax(Cube::y)
        val (minZ, maxZ) = minMax(Cube::z)
        val start = Cube(minX, minY, minZ)

        val steam = buildSet {
            add(start)
            val toVisit = mutableListOf(start)
            while (toVisit.isNotEmpty()) {
                val currentCube = toVisit.removeFirst()
                currentCube.neighbours()
                    .filter { neighbour ->
                        neighbour.x in minX..maxX &&
                            neighbour.y in minY..maxY &&
                            neighbour.z in minZ..maxZ &&
                            neighbour !in scan
                    }
                    .forEach { neighbour ->
                        add(neighbour) && toVisit.add(neighbour)
                    }
            }
        }

        return scan.sumOf { cube -> cube.neighbours().count { it in steam } }
    }

    private fun minMax(cubeProperty: KProperty1<Cube, Int>) =
        scan.map(cubeProperty).sorted().let { it.first() - 1 to it.last() + 1 }
}

private data class Cube(val x: Int, val y: Int, val z: Int) {
    fun neighbours() = setOf(
        Cube(x - 1, y, z),
        Cube(x + 1, y, z),
        Cube(x, y - 1, z),
        Cube(x, y + 1, z),
        Cube(x, y, z - 1),
        Cube(x, y, z + 1),
    )
}
