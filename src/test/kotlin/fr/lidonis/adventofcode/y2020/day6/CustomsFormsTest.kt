package fr.lidonis.adventofcode.y2020.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CustomsFormsTest {

    @Test
    fun sumOfYesAnyone() {
        val customsForms = CustomsForms(
            """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
        )
        assertThat(customsForms.sumOfYesAnyone()).isEqualTo(11)
    }

    @Test
    fun sumOfYesEveryOne() {
        val customsForms = CustomsForms(
            """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
        )
        assertThat(customsForms.sumOfYesEveryone()).isEqualTo(6)
    }
}