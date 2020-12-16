package fr.lidonis.adventofcode.y2020.day16

import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.tail

class TicketTranslation(private val lines: List<String>) {

    private val rules = mutableMapOf<String, List<IntRange>>()
    private val myTicket = mutableListOf<Int>()
    private val nearbyTickets = mutableListOf<List<Int>>()

    init {
        var scanRule = true
        var scanMyTicket = false
        var scanNearbyTickets = false
        for (line in lines) {
            when {
                line.isEmpty() -> continue
                line == "your ticket:" -> {
                    scanMyTicket = true
                    scanRule = false
                }
                line == "nearby tickets:" -> {
                    scanNearbyTickets = true
                    scanMyTicket = false
                }
                scanRule -> line.split(": ").let { (field, ranges) ->
                    rules[field] = ranges.split(" or ")
                        .map { it.split("-").let { (start, end) -> start.toInt()..end.toInt() } }
                        .toList()

                }
                scanMyTicket -> myTicket.addAll(line.split(",").map(String::toInt))
                scanNearbyTickets -> nearbyTickets.add(line.split(",").map(String::toInt))
            }
        }
    }

    fun scanningErrorRate(): Int {
        val allRanges = rules.values.flatten()
        return nearbyTickets.flatMap { ticket -> ticket.filter { i -> allRanges.none { it.contains(i) } } }.sum()
    }

    fun ticket(): Map<String, Int> {
        val allRanges = rules.values.flatten()
        val validTickets = nearbyTickets.filter { t -> t.all { i -> allRanges.any { it.contains(i) } } }
        val mappings = validTickets.head.map(this::matchingFields).toMutableList()
        for (ticket in validTickets.tail) {
            for ((i, value) in ticket.withIndex()) {
                mappings[i] = mappings[i].intersect(matchingFields(value))
            }
        }
        val fieldsMapping = mappings.asSequence()
            .mapIndexed { index, set -> index to set }
            .sortedByDescending { it.second.size }
            .windowed(2, partialWindows = true)
            .map { it.head.first to it.head.second - it.getOrElse(1) { -1 to emptySet() }.second }
            .sortedBy { it.first }.map { it.second.first() }.toList()
        return myTicket.mapIndexed { i, value -> fieldsMapping[i] to value }.toMap()
    }

    private fun matchingFields(value: Int) = rules.filterValues { ranges -> ranges.any { it.contains(value) } }.keys


}