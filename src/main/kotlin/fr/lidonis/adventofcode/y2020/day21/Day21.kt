package fr.lidonis.adventofcode.y2020.day21

import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.y2020.AdventOfCode2020

private const val DAY = 21

object Day21 : AdventOfCode2020(DAY) {

    private val allergenAssessment = AllergenAssessment(input().lines())

    @Answer("2786")
    override fun part1() = allergenAssessment.safeIngredient.map { safe ->
        allergenAssessment.foods.count { it.ingredients.contains(safe) }
    }.sum()

    @Answer("prxmdlz,ncjv,knprxg,lxjtns,vzzz,clg,cxfz,qdfpq")
    override fun part2() = allergenAssessment.canonical.joinToString(",")
}
