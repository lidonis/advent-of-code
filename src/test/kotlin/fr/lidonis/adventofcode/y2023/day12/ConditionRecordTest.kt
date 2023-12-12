package fr.lidonis.adventofcode.y2023.day12

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class ConditionRecordTest {

    @ParameterizedTest
    @ArgumentsSource(ConditionRecordProvider::class)
    fun `test count arrangements`(conditionRecord: ConditionRecord, expected: Int) {
        val result = conditionRecord.countArrangements()
        assertThat(result).isEqualTo(expected)
    }
}

private class ConditionRecordProvider : ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
        Arguments.of(ConditionRecord.of("# 1"), 1),
        Arguments.of(ConditionRecord.of("# 0"), 0),
        Arguments.of(ConditionRecord.of(". 0"), 1),
        Arguments.of(ConditionRecord.of(". 1"), 0),
        Arguments.of(ConditionRecord.of("? 1"), 1),
        Arguments.of(ConditionRecord.of("? 0"), 1),
        Arguments.of(ConditionRecord.of("## 2"), 1),
        Arguments.of(ConditionRecord.of("## 1"), 0),
        Arguments.of(ConditionRecord.of("## 0"), 0),
        Arguments.of(ConditionRecord.of(".. 0"), 1),
        Arguments.of(ConditionRecord.of(".. 1"), 0),
        Arguments.of(ConditionRecord.of(".. 2"), 0),
        Arguments.of(ConditionRecord.of("?? 0"), 1),
        Arguments.of(ConditionRecord.of("?? 1"), 2),
        Arguments.of(ConditionRecord.of("?? 2"), 1),
        Arguments.of(ConditionRecord.of("???.### 1,1,3"), 1),
        Arguments.of(ConditionRecord.of(".??..??...?##. 1,1,3"), 4),
        Arguments.of(ConditionRecord.of("?#?#?#?#?#?#?#? 1,3,1,6"), 1),
        Arguments.of(ConditionRecord.of("????.#...#... 4,1,1"), 1),
        Arguments.of(ConditionRecord.of("????.######..#####. 1,6,5"), 4),
        Arguments.of(ConditionRecord.of("?###???????? 3,2,1"), 10),
    )
}
