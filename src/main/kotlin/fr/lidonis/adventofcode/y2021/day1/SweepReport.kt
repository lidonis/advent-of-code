package fr.lidonis.adventofcode.y2021.day1

private const val WINDOW_SIZE = 3

class SweepReport(private val depths: List<Int>) {
    fun countIncrease() = count(depths)

    fun countWindowIncrease() = count(depths.windowed(WINDOW_SIZE).map(List<Int>::sum))

    private fun count(list: List<Int>) = list.zipWithNext().count { it.second > it.first }
}
