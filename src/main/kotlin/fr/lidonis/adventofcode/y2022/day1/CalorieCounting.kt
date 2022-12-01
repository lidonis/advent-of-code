package fr.lidonis.adventofcode.y2022.day1

class CalorieCounting(private val input: String) {

    private val calories: List<Int> by lazy {
        val result = mutableListOf(0)
        input.lines().forEach { line ->
            if(line.isBlank()) result.add(0)
            else result[result.lastIndex] += line.toInt()
        }
        result.sortedDescending()
    }

    fun mostCalories(take: Int): Int {
        return calories.take(take).sum()
    }

}
