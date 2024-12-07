package fr.lidonis.adventofcode.y2024.day7

object Combinator {

    fun combine(numbers: List<Long>, operations: List<Operation>): List<Long> {
        if (numbers.size == 1) return numbers

        val (first, second) = numbers.take(2)
        val remainingNumbers = numbers.drop(2)

        return operations.flatMap { operation ->
            val result = operation.eval(first, second)
            combine(listOf(result) + remainingNumbers, operations)
        }
    }
}

val OperationsPart1 = listOf(Operation.ADD, Operation.MULTIPLY)
val OperationsPart2 = Operation.entries

enum class Operation(val eval: (Long, Long) -> Long) {
    ADD(Long::plus),
    MULTIPLY(Long::times),
    CONCATENATION({ left, right -> "$left$right".toLong() })
}
