package fr.lidonis.adventofcode.y2020.day13

import fr.lidonis.adventofcode.common.math.chineseRemainder
import java.lang.Math.floorMod

class ShuttleSearch(private val lines: List<String>) {

    fun searchBus() = lines[0].toInt().let { timestamp ->
        lines[1].split(",").mapNotNull { it.toIntOrNull() }
            .map { busId -> busId to floorMod(-timestamp, busId) }
            .minByOrNull { (_, waitTime) -> waitTime }
    }

    fun findTimestamp() = sequence {
        for ((i, s) in lines[1].split(",").withIndex()) {
            s.toLongOrNull()?.let { yield(it to it - i) }
        }
    }.unzip().let { chineseRemainder(it.first, it.second) }
}
