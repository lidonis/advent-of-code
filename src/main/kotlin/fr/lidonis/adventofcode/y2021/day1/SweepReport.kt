package fr.lidonis.adventofcode.y2021.day1

class SweepReport(private val depths: List<Int>) {
    fun countIncrease() = count(depths)

    fun countWindowIncrease() = count(depths.windowed(3).map(List<Int>::sum))

    private fun count(list: List<Int>) = list.zipWithNext().count { it.second > it.first }
}