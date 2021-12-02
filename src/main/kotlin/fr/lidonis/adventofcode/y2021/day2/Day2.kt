package fr.lidonis.adventofcode.y2021.day2

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2021.AdventOfCode2021

private const val DAY = 2

object Day2 : AdventOfCode2021(DAY) {

    private val submarineCourse = SubmarineCourse(input().lines())

    @Answer("1815044")
    override fun part1() = submarineCourse.plannedCourse().let { it.x * it.y }

    @Answer("1739283308")
    override fun part2() = submarineCourse.plannedCourseWithAim().let { it.x * it.y }

}
