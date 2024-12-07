package fr.lidonis.adventofcode.y2024.day7

class BridgeRepair(lines: List<String>) {
    val equations = lines.asSequence()
        .map { it.split(": ") }
        .map { (test, numbers) ->
            test.toLong() to numbers.split(" ").map { it.toLong() }
        }

    fun part1() = totalCalibrationResult(OperationsPart1)

    fun totalCalibrationResult(operations: List<Operation>) =
        equations.asSequence()
            .filter { (test, numbers) ->
                canCombineToTestValue(numbers, test, operations)
            }.sumOf { it.first }

    private fun canCombineToTestValue(numbers: List<Long>, test: Long, operations: List<Operation>) =
        Combinator.combine(numbers, operations).any { it == test }

    fun part2() = totalCalibrationResult(OperationsPart2)
}
