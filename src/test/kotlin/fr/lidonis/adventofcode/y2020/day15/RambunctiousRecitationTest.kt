package fr.lidonis.adventofcode.y2020.day15

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RambunctiousRecitationTest {

    @Test
    fun lastSpokenDetailed() {
        assertThat(RambunctiousRecitation("0,3,6").lastSpoken(10)).isEqualTo(0)
    }

    @Test
    fun lastSpoken1() {
        assertThat(RambunctiousRecitation("0,3,6").lastSpoken(2020)).isEqualTo(436)
    }

    @Test
    fun lastSpoken2() {
        assertThat(RambunctiousRecitation("1,3,2").lastSpoken(2020)).isEqualTo(1)
    }

    @Test
    fun lastSpoken3() {
        assertThat(RambunctiousRecitation("2,1,3").lastSpoken(2020)).isEqualTo(10)
    }

    @Test
    fun lastSpoken4() {
        assertThat(RambunctiousRecitation("1,2,3").lastSpoken(2020)).isEqualTo(27)
    }

    @Test
    fun lastSpoken5() {
        assertThat(RambunctiousRecitation("2,3,1").lastSpoken(2020)).isEqualTo(78)
    }

    @Test
    fun lastSpoken6() {
        assertThat(RambunctiousRecitation("3,2,1").lastSpoken(2020)).isEqualTo(438)
    }

    @Test
    fun lastSpoken7() {
        assertThat(RambunctiousRecitation("3,1,2").lastSpoken(2020)).isEqualTo(1836)
    }
}