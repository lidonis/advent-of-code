package fr.lidonis.adventofcode.y2019.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class OrbitMapTest {

    @Test
    internal fun `count total orbits`() {
        val orbitMap = OrbitMap(
            """
            COM)B
            B)C
            C)D
            D)E
            E)F
            B)G
            G)H
            D)I
            E)J
            J)K
            K)L
        """.trimIndent()
        )
        assertThat(orbitMap.countTotalOrbits()).isEqualTo(42)
    }

    @Test
    fun `minimum number orbital transfers`() {
        val orbitMap = OrbitMap(
            """
            COM)B
            B)C
            C)D
            D)E
            E)F
            B)G
            G)H
            D)I
            E)J
            J)K
            K)L
            K)YOU
            I)SAN
        """.trimIndent()
        )
        assertThat(orbitMap.minimumOrbitalTransfers("YOU", "SAN")).isEqualTo(4)
    }
}
