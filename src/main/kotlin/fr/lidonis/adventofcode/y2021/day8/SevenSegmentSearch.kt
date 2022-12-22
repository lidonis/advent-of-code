package fr.lidonis.adventofcode.y2021.day8

class SevenSegmentSearch(private val lines: List<String>) {

    fun countUniqueSegmentNumber(): Int {
        return lines.flatMap { line ->
            line.split("|")[1].split(" ")
        }.count { it.length in 2..4 || it.length == 7 }
    }

    fun sumValues() = lines.sumOf { line ->
        val (lhs, rhs) = line.split(" | ", limit = 2)
        val signals = lhs.split(' ').map { it.toSet() }.groupBy { it.size }
        val one = signals.getValue(2).single()
        val four = signals.getValue(4).single()
        val seven = signals.getValue(3).single()
        val eight = signals.getValue(7).single()
        val (twos, threeFive) = signals.getValue(5).partition { (it - four).size == 3 }
        val two = twos.single()
        val (threes, fives) = threeFive.partition { (it - two).size == 1 }
        val three = threes.single()
        val five = fives.single()
        val (sixes, zeroNine) = signals.getValue(6).partition { (one - it).isNotEmpty() }
        val six = sixes.single()
        val (zeros, nines) = zeroNine.partition { (it - three).size == 2 }
        val nine = nines.single()
        val zero = zeros.single()
        val digits = arrayOf(zero, one, two, three, four, five, six, seven, eight, nine)
        rhs.split(' ').fold(0) { acc: Int, s -> 10 * acc + digits.indexOf(s.toSet()) }
    }
}
