package fr.lidonis.adventofcode.y2020.day23

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CrabCupsTest {

    @Test
    fun init() {
        assertThat(CrabCups("389125467").order).isEqualTo("25467389")
    }

    @Test
    fun `round 1`() {
        val crabCups = CrabCups("389125467")
        crabCups.play()
        assertThat(crabCups.order).isEqualTo("54673289")
    }

    @Test
    fun `round 2`() {
        val crabCups = CrabCups("389125467")
        crabCups.play(2)
        assertThat(crabCups.order).isEqualTo("32546789")
    }

    @Test
    fun `round 3`() {
        val crabCups = CrabCups("389125467")
        crabCups.play(3)
        assertThat(crabCups.order).isEqualTo("34672589")
    }

    @Test
    fun `round 10`() {
        val crabCups = CrabCups("389125467")
        crabCups.play(10)
        assertThat(crabCups.order).isEqualTo("92658374")
    }

    @Test
    fun `round 100`() {
        val crabCups = CrabCups("389125467")
        crabCups.play(100)
        assertThat(crabCups.order).isEqualTo("67384529")
    }

    @Test
    fun `size 1000000`() {
        val crabCups = CrabCups("389125467", 1_000_000)
        crabCups.play(10_000_000)
        val firstStar = crabCups.cupNextTo(1)
        val secondStar = crabCups.cupNextTo(firstStar)
        assertThat(firstStar).isEqualTo(934001)
        assertThat(secondStar).isEqualTo(159792)
        assertThat(firstStar.toLong() * secondStar.toLong()).isEqualTo(149245887792)
    }
}
