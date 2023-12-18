package fr.lidonis.adventofcode.y2020.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NavigationSystemTest {
    @Test
    fun distance() {
        val navigation = NavigationSystem(
            """
            F10
            N3
            F7
            R90
            F11
            """.trimIndent().lines()
        )
        assertThat(navigation.distanceDirection()).isEqualTo(25)
    }

    @Test
    fun distanceWaypoint() {
        val navigation = NavigationSystem(
            """
            F10
            N3
            F7
            R90
            F11
            """.trimIndent().lines()
        )
        assertThat(navigation.distanceWaypoint()).isEqualTo(286)
    }
}
