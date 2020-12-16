package fr.lidonis.adventofcode.y2020.day6

class CustomsForms(text: String) {

    private val groups = text.split("\n\n").map { it.split("\n").map(String::toSet) }

    fun sumOfYesAnyone() = groups.map { it.reduce { set, other -> set.union(other) }.size }.sum()

    fun sumOfYesEveryone() = groups.map { it.reduce { set, other -> set.intersect(other) }.size }.sum()
}
