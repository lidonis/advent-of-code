package fr.lidonis.adventofcode.y2023.day12

private const val REPEAT_PART_2 = 5

class HotSprings(lines: List<String>) {

    private val conditionRecords: List<ConditionRecord> = lines.map { ConditionRecord.of(it) }

    fun part1() = conditionRecords.sumOf(ConditionRecord::countArrangements)

    fun part2() = conditionRecords.map { record ->
        record.repeat(REPEAT_PART_2)
    }.sumOf(ConditionRecord::countArrangements)
}
