package fr.lidonis.adventofcode.y2022.day15

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2022.AdventOfCode2022

private const val DAY = 15

private const val NO_BEACON_ROW = 2_000_000
private const val AREA_SIZE = 4_000_000L

@Suppress("unused")
object Day15 : AdventOfCode2022(DAY) {

    private val beaconExclusionZone = BeaconExclusionZone(input().readLines())

    @Answer("4737443")
    override fun part1() = beaconExclusionZone.countPositionsNoBeacon(NO_BEACON_ROW)

    @Answer("11482462818989")
    override fun part2() =
        beaconExclusionZone.findDistressBeacon(AREA_SIZE)
            .let { it.x * AREA_SIZE + it.y }
}
