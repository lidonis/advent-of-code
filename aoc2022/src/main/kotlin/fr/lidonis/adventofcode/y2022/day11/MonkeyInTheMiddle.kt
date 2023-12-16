package fr.lidonis.adventofcode.y2022.day11

import fr.lidonis.adventofcode.common.multiply

private const val MONKEY_SIZE = 7

private const val ROUND_PART1 = 20
private const val ROUND_PART2 = 10000

private const val BORED_DIVISOR = 3

class MonkeyInTheMiddle(lines: List<String>) {

    private val monkeys = lines.chunked(MONKEY_SIZE).map { Monkey.fromString(it) }

    init {
        val product = monkeys.map { it.divisor }.multiply()
        for (monkey in monkeys) {
            monkey.product = product
            monkey.monkeys = monkeys
        }
    }

    fun monkeyBusiness() = rounds(ROUND_PART1, Monkey::inspectAndThrowBored)

    private fun rounds(rounds: Int, action: (Monkey) -> Unit): Long {
        repeat(rounds) {
            repeat(monkeys.size) {
                action(monkeys[it])
            }
        }
        val sortedInspected = monkeys.map { it.inspected }.sortedDescending()
        return sortedInspected[0] * sortedInspected[1]
    }

    fun monkeyBusinessNoWorries() = rounds(ROUND_PART2, Monkey::inspectAndThrowTrick)
}

private data class Monkey(
    val items: MutableList<Long>,
    val operation: (Long) -> Long,
    val divisor: Long,
    val monkeyTrue: Int,
    val monkeyFalse: Int
) {

    var monkeys: List<Monkey> = emptyList()
    var inspected = 0L
    var product = 0L

    fun inspectAndThrowBored() = inspectAndThrow { items[0] = items[0] / BORED_DIVISOR }

    private fun inspectAndThrow(manageWorry: () -> Unit) {
        repeat(items.size) {
            inspected++
            worry()
            manageWorry()
            throwItemToOtherMonkey()
        }
    }

    fun inspectAndThrowTrick() = inspectAndThrow { items[0] = items[0] % product }

    private fun worry() {
        items[0] = operation(items[0])
    }

    private fun throwItemToOtherMonkey() {
        val item = items.removeAt(0)
        val targetMonkey = test(item)
        monkeys[targetMonkey].receive(item)
    }

    private fun receive(item: Long) {
        items.add(item)
    }

    private fun test(item: Long) = if (item % divisor == 0L) monkeyTrue else monkeyFalse

    companion object {
        fun fromString(lines: List<String>): Monkey {
            var i = 1
            val items = extractItems(lines[i++])
            val operation = extractOperation(lines[i++])
            val divisor = extractDivisor(lines[i++])
            val monkeyTrue = extractMonkeyTrue(lines[i++])
            val monkeyFalse = extractMonkeyFalse(lines[i])
            return Monkey(items, operation, divisor, monkeyTrue, monkeyFalse)
        }

        private fun extractItems(line: String) =
            line.substringAfter("Starting items: ").split(", ").map { it.toLong() }.toMutableList()

        private fun extractOperation(line: String): (Long) -> Long {
            val operationString = line.substringAfter("Operation: new = old ")
            val number = operationString.drop(2)
            val operation = when {
                operationString[0] == '*' && number == "old" -> {
                    { i: Long -> i * i }
                }

                operationString[0] == '*' -> {
                    { i: Long -> i * number.toLong() }
                }

                else -> {
                    { i: Long -> i + number.toLong() }
                }
            }
            return operation
        }

        private fun extractDivisor(line: String) = line.substringAfter("Test: divisible by ").toLong()
        private fun extractMonkeyTrue(line: String) =
            line.substringAfter("If true: throw to monkey ").toInt()

        private fun extractMonkeyFalse(line: String) =
            line.substringAfter("If false: throw to monkey ").toInt()
    }
}
