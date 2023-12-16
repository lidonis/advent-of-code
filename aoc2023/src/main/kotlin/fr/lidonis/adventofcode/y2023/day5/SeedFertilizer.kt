package fr.lidonis.adventofcode.y2023.day5

class SeedFertilizer(lines: String) {

    private val groups = lines.split("\n\n")

    private val seeds = groups[0].split(" ").drop(1).map { it.toLong() }

    private val maps = groups.drop(1).map { group ->
        group.split("\n").drop(1).map { line -> line.split(" ").map { it.toLong() } }
            .map { (from, to, length) -> Convert(from, to, length) }.sortedBy { it.source }
    }

    fun part1() = seeds
        .map { it..it }
        .flatMap(::applyRangeConversions)
        .minOf { it.first }

    fun part2() = seeds
        .chunked(2)
        .map { (first, second) -> (first..first + second) }
        .flatMap(::applyRangeConversions)
        .minOf { it.first }

    private fun applyRangeConversions(inputRange: LongRange) =
        maps.fold(listOf(inputRange), ::convert)

    private fun convert(ranges: List<LongRange>, converts: List<Convert>) =
        ranges.flatMap { range -> converts.convertRanges(range) }

    private fun List<Convert>.convertRanges(range: LongRange): List<LongRange> {
        var startOfRange = range.first
        val resultRanges = mutableListOf<LongRange>()

        for (overlappedConversion in this) {
            if (overlappedConversion.overlapsWith(range)) {
                resultRanges.addNonOverlappingRange(startOfRange, overlappedConversion.source)
                startOfRange = resultRanges.applyOverlappingRange(startOfRange, range.last + 1, overlappedConversion)
            }
        }

        resultRanges.addRemainingRangeIfNecessary(startOfRange, range.last)

        return resultRanges
    }

    private fun MutableList<LongRange>.addNonOverlappingRange(startOfRange: Long, overlappedStart: Long) {
        if (startOfRange < overlappedStart) {
            add(startOfRange until overlappedStart)
        }
    }

    private fun MutableList<LongRange>.applyOverlappingRange(
        startOfRange: Long,
        rangeEnd: Long,
        overlappedConversion: Convert
    ): Long {
        val start = startOfRange.coerceAtLeast(overlappedConversion.source)
        val end = rangeEnd.coerceAtMost(overlappedConversion.source + overlappedConversion.length)
        add(start + overlappedConversion.delta until end + overlappedConversion.delta)
        return end
    }

    private fun MutableList<LongRange>.addRemainingRangeIfNecessary(startOfRange: Long, currentRangeEnd: Long) {
        if (startOfRange <= currentRangeEnd) {
            add(startOfRange..currentRangeEnd)
        }
    }

    data class Convert(val destination: Long, val source: Long, val length: Long) {
        val delta = destination - source

        fun overlapsWith(range: LongRange) =
            !range.isEmpty() && source <= range.last && range.first < source + length
    }
}
