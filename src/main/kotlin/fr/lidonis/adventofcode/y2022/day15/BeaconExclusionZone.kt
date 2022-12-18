package fr.lidonis.adventofcode.y2022.day15

import fr.lidonis.adventofcode.common.geo.plane.Position
import kotlin.math.abs


private const val INPUT_REGEX = """Sensor at x=(-?\d+), y=(-?\d+): closest beacon is at x=(-?\d+), y=(-?\d+)"""

class BeaconExclusionZone(lines: List<String>) {

    private val sensors = lines
        .asSequence()
        .mapNotNull { line ->
            INPUT_REGEX.toRegex().matchEntire(line)?.groups?.drop(1)?.mapNotNull { it?.value?.toInt() }
        }
        .map { (xBeacon, yBeacon, xSensor, ySensor) ->
            Sensor(
                position = Position(xBeacon, yBeacon),
                closestBeacon = Position(xSensor, ySensor)
            )
        }
        .toSet()

    private val sensorsPositions = sensors.map { it.position }.toSet()
    private val beaconsPositions = sensors.map { it.closestBeacon }.toSet()
    private val allKnownPositions = sensorsPositions + beaconsPositions

    fun countPositionsNoBeacon(noBeaconRow: Int): Any {
        val beaconsInRow = beaconsPositions
            .map { it.y }
            .filter { it == noBeaconRow }
            .toSet()

        val noBeacon = sensors
            .map { sensor -> sensor.position.x to sensor.range - abs(noBeaconRow - sensor.position.y) }
            .flatMap { (x, deltaToRow) ->
                ((x - deltaToRow)..(x + deltaToRow)).toSet()
            }
            .toSet()
            .subtract(beaconsInRow)

        return noBeacon.count()
    }

    fun findDistressBeacon(areaSize: Long) = sensors.asSequence().flatMap {
        it.findDistressBeacon(areaSize)
    }.first()

    private fun Sensor.findDistressBeacon(maxSearchIndex: Long) = sequence {
        val outlineDistance = range + 1
        val minY = position.y - outlineDistance
        val maxY = position.y + outlineDistance
        (minY..maxY).forEach { y ->
            val offset = if (y <= position.y) {
                y - minY
            } else {
                maxY - y
            }
            Position(position.x - offset, y).let {
                if (it.isDistressBeacon(maxSearchIndex)) yield(it)
            }
            Position(position.x + offset, y).let {
                if (it.isDistressBeacon(maxSearchIndex)) yield(it)
            }
        }
    }

    private fun Position.isDistressBeacon(areaSize: Long) =
        this !in allKnownPositions &&
                x in 0..areaSize &&
                y in 0..areaSize &&
                sensors.none { sensor -> sensor.isInRange(this) }

}

data class Sensor(val position: Position, val closestBeacon: Position) {
    val range = position.manhattanDistance(closestBeacon)

    fun isInRange(position: Position): Boolean {
        return this.position.manhattanDistance(position) <= range
    }
}
