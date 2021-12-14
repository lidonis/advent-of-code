package fr.lidonis.adventofcode.y2021.day14

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ExtendedPolymerizationTest {

    val example = """
        NNCB

        CH -> B
        HH -> N
        CB -> H
        NH -> C
        HB -> C
        HC -> B
        HN -> C
        NN -> C
        BH -> H
        NC -> B
        NB -> B
        BN -> B
        BB -> N
        BC -> B
        CC -> N
        CN -> C
    """.trimIndent()

    @Test
    internal fun `quantity of the most common element and subtract the quantity of the least common element`() {
        val extendedPolymerization = ExtendedPolymerization(example.lines())
        val result = extendedPolymerization.mostAndLeastCommonElements(10)
        assertThat(result).isEqualTo(1588)
    }
}
