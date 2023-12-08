package fr.lidonis.adventofcode.y2023.day7

import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.util.stream.Stream

class HandJokerProvider : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
        Arguments.of("QJJQJ", HandType.FIVE_OF_A_KIND),
        Arguments.of("JJKKK", HandType.FIVE_OF_A_KIND),
        Arguments.of("TJJJJ", HandType.FIVE_OF_A_KIND),
        Arguments.of("JJJJJ", HandType.FIVE_OF_A_KIND),
        Arguments.of("QJJQ2", HandType.FOUR_OF_A_KIND),
        Arguments.of("T55J5", HandType.FOUR_OF_A_KIND),
        Arguments.of("KTJJT", HandType.FOUR_OF_A_KIND),
        Arguments.of("QQQJA", HandType.FOUR_OF_A_KIND),
        Arguments.of("J2233", HandType.FULL_HOUSE),
        Arguments.of("JJ123", HandType.THREE_OF_A_KIND),
        Arguments.of("1234J", HandType.ONE_PAIR),
    )
}
