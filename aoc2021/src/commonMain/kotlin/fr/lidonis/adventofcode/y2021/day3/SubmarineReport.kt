package fr.lidonis.adventofcode.y2021.day3

class SubmarineReport(private val lines: List<String>) {

    private val size = lines.minOfOrNull { it.length } ?: 0

    fun powerConsumption(): Int {
        val common = CharArray(size, lines::mostCommon)
        val gamma = common.toInt(2)
        common.forEachIndexed { i, c -> common[i] = (c.code xor 1).toChar() }
        val epsilon = common.toInt(2)
        return gamma * epsilon
    }

    fun lifeSupportRating(): Int {
        val o2 = lines.toMutableList()
        val co2 = lines.toMutableList()
        for (i in 0 until size) {
            val common = o2.mostCommon(i)
            val uncommon = co2.leastCommon(i)
            o2.retainAll { it[i] == common }
            co2.retainAll { it[i] == uncommon }
        }
        return o2.single().toInt(2) * co2.single().toInt(2)
    }
}

val comparator: Comparator<Map.Entry<Char, Int>> = compareBy({ it.value }, { it.key })

private fun List<String>.mostCommon(i: Int) =
    groupingBy { it[i] }.eachCount().entries.maxWithOrNull(comparator)!!.key

private fun List<String>.leastCommon(i: Int) =
    groupingBy { it[i] }.eachCount().entries.minWithOrNull(comparator)!!.key

private fun CharArray.toInt(radix: Int) = concatToString().toInt(radix)
