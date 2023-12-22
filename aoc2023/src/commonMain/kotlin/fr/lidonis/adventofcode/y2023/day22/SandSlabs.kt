package fr.lidonis.adventofcode.y2023.day22

class SandSlabs(private val lines: List<String>) {

    private val bricks = lines
        .asSequence()
        .map { it.split("~") }
        .map { (start, end) -> Segment(Point3D.parse(start), Point3D.parse(end)) }
        .toSet()

    fun part1() {
        // make pieces fall with gravity but not into other pieces
        val fall = fall().zipWithNext().takeWhile { it.first != it.second }.last().first
        fall.forEach { println(it) }
    }

    fun fall() = generateSequence(bricks) {
        it.map { brick ->
            val fall = brick.fall()
            if(it.none { it != brick && it.findIntersection(fall) != null } && fall.end.z >= 0 && fall.start.z >= 0) {
                fall
            } else {
                brick
            }
        }.toSet()
    }

    fun part2() = lines.size
}

data class Segment(val start: Point3D, val end: Point3D){

    fun fall() = Segment(start.fall(), end.fall())

    fun findIntersection(other: Segment): Point3D? {
        val dir1 = Point3D(end.x - start.x, end.y - start.y, end.z - start.z)
        val dir2 = Point3D(other.end.x - other.start.x, other.end.y - other.start.y, other.end.z - other.start.z)

        val crossProduct = Point3D(
            dir1.y * dir2.z - dir1.z * dir2.y,
            dir1.z * dir2.x - dir1.x * dir2.z,
            dir1.x * dir2.y - dir1.y * dir2.x
        )

        val denominator = crossProduct.x * crossProduct.x + crossProduct.y * crossProduct.y + crossProduct.z * crossProduct.z

        if (denominator == 0) {
            // Les segments sont parallèles ou colinéaires
            return null
        }

        val w = Point3D(other.start.x - start.x, other.start.y - start.y, other.start.z - start.z)

        val t = (w.x * crossProduct.x + w.y * crossProduct.y + w.z * crossProduct.z) / denominator

        if (t < 0.0 || t > 1.0) {
            // L'intersection se situe en dehors des segments
            return null
        }

        val intersectionPoint = Point3D(
            start.x + t * dir1.x,
            start.y + t * dir1.y,
            start.z + t * dir1.z
        )

        return intersectionPoint
    }
}

data class Point3D(val x: Int, val y: Int, val z: Int) {
    fun fall() = Point3D(x, y, z - 1)

    companion object {
        fun parse(start: String): Point3D {
            val (x, y, z) = start.split(",")
            return Point3D(x.toInt(), y.toInt(), z.toInt())
        }
    }
}
