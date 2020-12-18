package fr.lidonis.adventofcode.y2020.day18

import java.util.ArrayDeque

class OperationOrder(private val lines: List<String>) {

    fun sum1() = sum(ExpressionEvaluator1())

    fun sum2() = sum(ExpressionEvaluator2())

    private fun sum(expressionEvaluator: ExpressionEvaluator) = expressionEvaluator.let {
        lines.map(it::evaluate).sum()
    }
}

abstract class ExpressionEvaluator {

    fun evaluate(expression: String): Long {
        val tokens = expression.toCharArray()
        val values = ArrayDeque<Long>()
        val ops = ArrayDeque<Char>()
        var i = 0
        while (i < tokens.size) {
            when (tokens[i]) {
                in '0'..'9' -> {
                    val sbuf = StringBuffer()
                    while (i < tokens.size && tokens[i] in '0'..'9') sbuf.append(tokens[i++])
                    i--
                    values.push(sbuf.toString().toLong())
                }
                '(' -> ops.push(tokens[i])
                ')' -> {
                    while (ops.peek() != '(') values.push(
                        applyOperation(ops.pop(), values.pop(), values.pop())
                    )
                    ops.pop()
                }
                '+', '*' -> {
                    while (ops.isNotEmpty() && hasPrecedence(tokens[i], ops.peek())) {
                        values.push(applyOperation(ops.pop(), values.pop(), values.pop()))
                    }
                    ops.push(tokens[i])
                }
            }
            i++
        }

        while (ops.isNotEmpty()) {
            values.push(applyOperation(ops.pop(), values.pop(), values.pop()))
        }
        return values.pop()
    }

    protected abstract fun hasPrecedence(op1: Char, op2: Char): Boolean

    private fun applyOperation(op: Char, b: Long, a: Long) = when (op) {
        '+' -> a + b
        '*' -> a * b
        else -> error("Invalid operation")
    }
}

class ExpressionEvaluator1 : ExpressionEvaluator() {
    override fun hasPrecedence(op1: Char, op2: Char): Boolean = op2 != '(' && op2 != ')'
}

class ExpressionEvaluator2 : ExpressionEvaluator() {
    override fun hasPrecedence(op1: Char, op2: Char) = if (op2 == '(' || op2 == ')') false
    else !((op2 == '*' || op2 == '/') && (op1 == '+' || op1 == '-'))
}
