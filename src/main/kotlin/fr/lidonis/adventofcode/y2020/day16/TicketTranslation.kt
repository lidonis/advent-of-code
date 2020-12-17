package fr.lidonis.adventofcode.y2020.day16

@OptIn(ExperimentalStdlibApi::class)
class TicketTranslation(lines: List<String>) {

    private val rules: Map<String, Set<IntRange>>
    private val allFields: Set<String>
    private val allRanges: Set<IntRange>
    private val myTicket: List<Int>
    private val nearbyTickets: List<List<Int>>
    private val matchingFieldsCache = mutableMapOf<Int, Set<String>>()

    init {
        val lineIterator = lines.iterator()
        rules = buildMap(readRules(lineIterator))
        allRanges = rules.values.flatten().toSet()
        allFields = rules.keys
        check(lineIterator.next() == "your ticket:")
        myTicket = lineIterator.next().split(",").map(String::toInt)
        check(lineIterator.next().isEmpty())
        check(lineIterator.next() == "nearby tickets:")
        nearbyTickets = buildList(readNearbyTickets(lineIterator))
    }

    private fun readRules(lineIterator: Iterator<String>): MutableMap<String, Set<IntRange>>.() -> Unit = {
        for (line in lineIterator) {
            if (line.isEmpty()) break
            line.split(": ").let { (field, ranges) ->
                this[field] = ranges.split(" or ").map {
                    val (start, end) = it.split("-")
                    start.toInt()..end.toInt()
                }.toSet()
            }
        }
    }

    private fun readNearbyTickets(lineIterator: Iterator<String>): MutableList<List<Int>>.() -> Unit = {
        for (line in lineIterator) {
            this.add(line.split(',').map { it.toInt() })
        }
    }

    fun scanningErrorRate() = nearbyTickets.flatMap { ticket ->
        ticket.filter { allRanges.none { range -> range.contains(it) } }
    }.sum()

    fun ticket() = this.fieldsMapping().let { fieldsMapping ->
        myTicket.mapIndexed { i, value -> fieldsMapping[i] to value }.toMap()
    }

    private fun fieldsMapping(): List<String> {
        val compatibilitiesBySize = compatibilities().withIndex().sortedBy { it.value.size }
        val matchedFields = mutableSetOf<String>()
        val fieldsMapping = MutableList(allFields.size) { "" }
        for ((index, fields) in compatibilitiesBySize) {
            fieldsMapping[index] = (fields - matchedFields).first()
            matchedFields.add(fieldsMapping[index])
        }
        return fieldsMapping
    }

    private fun compatibilities(): List<Set<String>> {
        return validTickets().fold(MutableList(allFields.size) { allFields }) { compatibilities, ticket ->
            for ((i, value) in ticket.withIndex()) {
                compatibilities[i] = compatibilities[i].intersect(matchingFields(value))
            }
            compatibilities
        }
    }

    private fun validTickets() = nearbyTickets.filter { t -> t.all { i -> allRanges.any { it.contains(i) } } }

    private fun matchingFields(value: Int) =
        matchingFieldsCache.getOrPut(value) { rules.filterValues { ranges -> ranges.any { it.contains(value) } }.keys }
}
