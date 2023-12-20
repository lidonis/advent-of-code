package fr.lidonis.adventofcode.y2023.day7

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

class HandTest {

    @ParameterizedTest
    @ArgumentsSource(HandProvider::class)
    fun `should compute hand type`(input: String, expectedHandType: HandType) {
        assertThat(Hand(input).type()).isEqualTo(expectedHandType)
    }

    @Test
    fun `should order by value for same type`() {
        assertThat(Hand("33332") > (Hand("2AAAA"))).isTrue()
    }
}
