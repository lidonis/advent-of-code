package fr.lidonis.adventofcode.y2020.day21

import kotlin.String as Allergen
import kotlin.String as Ingredient

private val LINE_REGEX = Regex("""(.+) \(contains (.+)\)""")

class AllergenAssessment(lines: List<kotlin.String>) {

    val foods = lines.map(Food.Companion::fromString)
    val safeIngredient by lazy {
        val ingredients = foods.flatMap { it.ingredients }.toSet()
        ingredients - canonical
    }
    val canonical by lazy {
        val allergens = foods.flatMap { it.allergens }.toSet()
        solve(
            allergens.associateWith(this::ingredientsWhichCanContainAllergen),
            emptyMap()
        ).toSortedMap().values
    }

    private fun ingredientsWhichCanContainAllergen(allergen: Allergen): Set<Ingredient> = foods
        .filter { it.allergens.contains(allergen) }
        .map { it.ingredients }
        .reduce { acc, set -> acc intersect set }

    private fun solve(
        remaining: Map<Allergen, Set<Ingredient>>,
        acc: Map<Allergen, Ingredient>
    ): Map<Allergen, Ingredient> {
        if (remaining.isEmpty()) return acc
        remaining.filterValues { it.size == 1 }.entries.first().let { (allergen, ingredientSet) ->
            return ingredientSet.first().let { ingredient ->
                solve(
                    (remaining - allergen).mapValues { it.value - ingredient },
                    acc + (allergen to ingredient)
                )
            }
        }
    }

    data class Food(val ingredients: Set<Ingredient>, val allergens: Set<Allergen>) {
        companion object {
            fun fromString(line: kotlin.String) =
                LINE_REGEX.matchEntire(line)?.destructured?.let { (ingredients, allergens) ->
                    Food(
                        ingredients.split(" ").toSet(),
                        allergens.split(", ").toSet()
                    )
                } ?: error("Invalid food")
        }
    }
}
