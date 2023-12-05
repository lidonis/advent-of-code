package fr.lidonis.adventofcode.y2023.day5

class SeedFertilizer(lines: String) {

    private val groups = lines.split("\n\n")

    private val seeds = groups[0].split(" ").drop(1).map { it.toLong() }

    private val maps = groups.drop(1).map { group ->
        group.split("\n").drop(1).map { line -> line.split(" ").map { it.toLong() } }
            .map { (from, to, length) -> Convert(from, to, length) }
    }

    fun part1() = seeds.mapNumberAll()

    private fun List<Long>.mapNumberAll() =
        this.asSequence().map { it.mapNumberAll() }.min()

    fun part2(): Long {
        val seedsRange = seeds.chunked(2).map { (first, second) ->
            (first..first + second)
        }
        return seedsRange.asSequence().map { range ->
            range.asSequence().map { it.mapNumberAll() }.min()
        }.min()
    }

    private fun Long.mapNumberAll(): Long {
        var result = this
        for (map in maps) {
            result = map.mapNumber(result)
        }
        return result
    }

    private fun List<Convert>.mapNumber(result: Long) =
        find { result in it.range }?.let { result + it.delta } ?: result

    data class Convert(val destination: Long, val source: Long, val length: Long) {
        val delta = destination - source
        val range = source..source + length
    }
}
