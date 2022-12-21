package fr.lidonis.adventofcode.y2022.day21

import space.kscience.kmath.functions.ListRationalFunction
import space.kscience.kmath.functions.ListRationalFunctionSpace
import space.kscience.kmath.functions.listRationalFunctionSpace
import space.kscience.kmath.operations.JBigIntegerField
import space.kscience.kmath.operations.Ring
import java.math.BigInteger

private const val ROOT_NAME = "root"
private const val HUMAN_NAME = "humn"

class MonkeyMath(lines: List<String>) {

    private val monkeys = lines
        .map { it.split(": ") }
        .map(::parseMonkey)
        .associateBy(Monkey::name)

    private fun parseMonkey(line: List<String>) =
        line.let { (name, job) ->
            job.toLongOrNull()
                ?.let {
                    when (name) {
                        HUMAN_NAME -> Human(it)
                        else -> NumberMonkey(name, it)
                    }
                }
                ?: ComputeMonkey(name, job)
        }

    private val root = monkeys[ROOT_NAME] as ComputeMonkey

    fun root() = with(MonkeysContext(monkeys)) { root.yell() }

    fun human(): Long {
        return with(MonkeysContext(monkeys)) {
            val bigIntegerFunctionSpace: ListRationalFunctionSpace<BigInteger, Ring<BigInteger>> =
                JBigIntegerField.listRationalFunctionSpace
            with(bigIntegerFunctionSpace) {
                val left = root.monkey1().rationalFunctions()
                val right = root.monkey2().rationalFunctions()

                val equationXEqualZero = left.numerator * right.denominator - right.numerator * left.denominator

                -equationXEqualZero.coefficients[0] / equationXEqualZero.coefficients[1]
            }
        }.toLong()
    }
}

private class MonkeysContext(
    val monkeys: Map<String, Monkey>
)

private sealed interface Monkey {
    val name: String

    context(MonkeysContext)
    fun yell(): Long

    context(ListRationalFunctionSpace<BigInteger, Ring<BigInteger>>, MonkeysContext)
    fun rationalFunctions(): ListRationalFunction<BigInteger>
}

private open class NumberMonkey(
    override val name: String,
    val number: Long
) : Monkey {
    context(MonkeysContext)
    override fun yell() = number

    context(ListRationalFunctionSpace<BigInteger, Ring<BigInteger>>, MonkeysContext)
    override fun rationalFunctions() =
        ListRationalFunction(listOf(number.toBigInteger()))

}

private open class ComputeMonkey(
    override val name: String,
    operation: String
) : Monkey {

    context(MonkeysContext)
    fun monkey1(): Monkey = monkeys[monkey1Name]!!

    context(MonkeysContext)
    fun monkey2(): Monkey = monkeys[monkey2Name]!!

    private val split = operation.split(" ")

    private val monkey1Name = split[0]
    val operator = split[1].first()
    private val monkey2Name = split[2]

    context(MonkeysContext)
    override fun yell() =
        operation(monkey1().yell(), monkey2().yell())

    fun operation(monkey1Number: Long, monkey2Number: Long): Long = when (operator) {
        '+' -> monkey1Number + monkey2Number
        '-' -> monkey1Number - monkey2Number
        '*' -> monkey1Number * monkey2Number
        '/' -> monkey1Number / monkey2Number
        else -> error("wrong operation")
    }

    context(ListRationalFunctionSpace<BigInteger, Ring<BigInteger>>, MonkeysContext)
    override fun rationalFunctions() =
        operation(monkey1().rationalFunctions(), monkey2().rationalFunctions())

    context(ListRationalFunctionSpace<BigInteger, Ring<BigInteger>>)
    private fun operation(
        monkeyFunction1: ListRationalFunction<BigInteger>,
        monkeyFunction2: ListRationalFunction<BigInteger>
    ) = when (operator) {
        '+' -> monkeyFunction1 + monkeyFunction2
        '-' -> monkeyFunction1 - monkeyFunction2
        '*' -> monkeyFunction1 * monkeyFunction2
        '/' -> monkeyFunction1 / monkeyFunction2
        else -> error("wrong operation")
    }
}

private class Human(number: Long) : NumberMonkey(HUMAN_NAME, number) {
    context(ListRationalFunctionSpace<BigInteger, Ring<BigInteger>>, MonkeysContext)
    override fun rationalFunctions() =
        ListRationalFunction(listOf(ring.zero, ring.one))
}
