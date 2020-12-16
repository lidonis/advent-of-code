package fr.lidonis.adventofcode.y2020.day1

import fr.lidonis.adventofcode.common.combine

class ExpenseReport(private val entries: List<Int>) {

    fun findCombinationBySum(size: Int, sum: Int) = entries.combine(size).firstOrNull { it.sum() == sum }

}
