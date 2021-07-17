package fr.lidonis.adventofcode.y2020.day25

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ComboBreakerTest {

    @Test
    fun findCardLoopSize() {
        assertThat(ComboBreaker().findLoopSize(5764801)).isEqualTo(8)
    }

    @Test
    fun findDoorLoopSize() {
        assertThat(ComboBreaker().findLoopSize(17807724)).isEqualTo(11)
    }

    @Test
    fun computeEncryptionKeyWithDoorKey() {
        assertThat(ComboBreaker(17807724).computeEncryptionKey(8)).isEqualTo(14897079)
    }

    @Test
    fun computeEncryptionKeyWithCardKey() {
        assertThat(ComboBreaker(5764801).computeEncryptionKey(11)).isEqualTo(14897079)
    }

}
