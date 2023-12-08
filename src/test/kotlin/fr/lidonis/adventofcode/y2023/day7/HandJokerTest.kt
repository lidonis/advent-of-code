package fr.lidonis.adventofcode.y2023.day7

import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

class HandJokerTest {

    @ParameterizedTest
    @ArgumentsSource(HandJokerProvider::class)
    fun `should compute hand type for joker hand`(input: String, expectedHandType: HandType) {
        Assertions.assertThat(HandJoker(input).type()).isEqualTo(expectedHandType)
    }

    @ParameterizedTest
    @ArgumentsSource(HandProvider::class)
    fun `should compute hand type for no joker hand`(input: String, expectedHandType: HandType) {
        Assertions.assertThat(HandJoker(input).type()).isEqualTo(expectedHandType)
    }
}
