package fr.lidonis.adventofcode.y2022.day6

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TuningTroubleTest {

    @Test
    internal fun example1() {
        val input = "bvwbjplbgvbhsrlpgdmjqwftvncz"
        val result = TuningTrouble(input).startOfMessage(4)
        assertThat(result).isEqualTo(5)
    }

    @Test
    internal fun example2() {
        val input = "nppdvjthqldpwncqszvftbrmjlhg"
        val result = TuningTrouble(input).startOfMessage(4)
        assertThat(result).isEqualTo(6)
    }

    @Test
    internal fun example3() {
        val input = "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg"
        val result = TuningTrouble(input).startOfMessage(4)
        assertThat(result).isEqualTo(10)
    }

    @Test
    internal fun example4() {
        val input = "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
        val result = TuningTrouble(input).startOfMessage(4)
        assertThat(result).isEqualTo(11)
    }
}
