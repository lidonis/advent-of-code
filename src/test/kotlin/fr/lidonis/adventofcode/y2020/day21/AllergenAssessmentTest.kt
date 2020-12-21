package fr.lidonis.adventofcode.y2020.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class AllergenAssessmentTest {

    private val allergenAssessment = AllergenAssessment(
        """
            mxmxvkd kfcds sqjhc nhms (contains dairy, fish)
            trh fvjkl sbzzf mxmxvkd (contains dairy)
            sqjhc fvjkl (contains soy)
            sqjhc mxmxvkd sbzzf (contains fish)
        """.trimIndent().lines()
    )

    @Test
    fun `safe ingredients`() {
        assertThat(allergenAssessment.safeIngredient).isEqualTo(setOf("kfcds", "nhms", "sbzzf", "trh"))
    }

    @Test
    fun `canonical dangerous ingredient list`() {
        assertThat(allergenAssessment.canonical).isEqualTo(setOf("mxmxvkd", "sqjhc", "fvjkl"))
    }
}