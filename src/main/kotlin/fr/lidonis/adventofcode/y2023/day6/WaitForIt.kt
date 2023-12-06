package fr.lidonis.adventofcode.y2023.day6

import fr.lidonis.adventofcode.common.multiply

private val NUMBER_REGEX = """\d+""".toRegex()

class WaitForIt(private val lines: List<String>) {

    fun part1(): Int {
        val times = lines[0].findNumbers()
        val distances = lines[1].findNumbers()

        return times.zip(distances)
            .map { (time, distance) ->
                count(time, distance)
            }.multiply()
    }

    private fun count(time: Long, distance: Long) =
        (1L until time).count { i -> (time - i) * i > distance }
    private fun String.findNumbers() = NUMBER_REGEX.findAll(this).map { it.value.toLong() }

    fun part2() = count(lines[0].getNumber(), lines[1].getNumber())

    private fun String.getNumber() = filter { it.isDigit() }.toLong()
}
