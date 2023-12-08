package fr.lidonis.adventofcode.y2023.day7

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class HandProvider : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
        Arguments.of("AAAAA", HandType.FIVE_OF_A_KIND),
        Arguments.of("AAAA2", HandType.FOUR_OF_A_KIND),
        Arguments.of("AAA22", HandType.FULL_HOUSE),
        Arguments.of("AAA23", HandType.THREE_OF_A_KIND),
        Arguments.of("AA223", HandType.TWO_PAIRS),
        Arguments.of("AA234", HandType.ONE_PAIR),
        Arguments.of("A2345", HandType.HIGH_CARD)
    )
}
