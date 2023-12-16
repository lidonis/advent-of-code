package fr.lidonis.adventofcode.y2023.day12

import fr.lidonis.adventofcode.common.head
import fr.lidonis.adventofcode.common.tail

private val memoized = mutableMapOf<Pair<ConditionRecord, Boolean>, Long>()

data class ConditionRecord(val row: Row, val criteria: Criteria) {
    fun countArrangements(inDamagedGroup: Boolean = false): Long =
        memoized.getOrPut(this to inDamagedGroup) { count(inDamagedGroup) }

    private fun count(inDamagedGroup: Boolean = false) =
        if (row.conditions.size == 1) {
            terminalCase()
        } else {
            recursion(inDamagedGroup)
        }

    private fun recursion(inDamagedGroup: Boolean): Long {
        val conditionType = row.conditions.first()
        return when (conditionType) {
            ConditionType.OPERATIONAL -> {
                operational(inDamagedGroup)
            }

            ConditionType.DAMAGED -> {
                damaged(inDamagedGroup)
            }

            ConditionType.UNKNOWN -> {
                damaged(inDamagedGroup) +
                    operational(inDamagedGroup)
            }
        }
    }

    private fun terminalCase(): Long {
        val conditionType = row.conditions.single()
        val criterion = criteria.lengths.singleOrNull() ?: 0L
        return when {
            criteria.lengths.size > 1L -> {
                0L
            }

            conditionType == ConditionType.OPERATIONAL && criterion == 0L -> 1L
            conditionType == ConditionType.DAMAGED && criterion == 1L -> 1L
            conditionType == ConditionType.UNKNOWN && criterion == 1L -> 1L
            conditionType == ConditionType.UNKNOWN && criterion == 0L -> 1L
            else -> {
                0L
            }
        }
    }

    private fun damaged(inDamagedGroup: Boolean) =
        when {
            inDamagedGroup && criteria.lengths.head == 0L -> {
                0L
            }

            else -> {
                val criterion = criteria.lengths.firstOrNull() ?: 0L
                ConditionRecord(
                    row = Row(row.conditions.drop(1)),
                    criteria = Criteria(listOf(criterion - 1L) + criteria.lengths.tail)
                ).countArrangements(true)
            }
        }

    private fun operational(inDamagedGroup: Boolean) =
        when {
            inDamagedGroup && criteria.lengths.head != 0L -> {
                0L
            }

            inDamagedGroup && criteria.lengths.head == 0L -> {
                ConditionRecord(
                    row = Row(row.conditions.drop(1)),
                    criteria = Criteria(criteria.lengths.tail)
                ).countArrangements(false)
            }

            else -> {
                ConditionRecord(
                    row = Row(row.conditions.drop(1)),
                    criteria = Criteria(criteria.lengths)
                ).countArrangements(false)
            }
        }

    fun repeat(n: Int): ConditionRecord {
        return ConditionRecord(
            row = Row(row.conditions.repeatList(n, ConditionType.UNKNOWN)),
            criteria = Criteria(criteria.lengths.repeatList(n))
        )
    }

    @JvmInline
    value class Row(val conditions: List<ConditionType>)

    enum class ConditionType(val c: Char) {
        OPERATIONAL('.'), DAMAGED('#'), UNKNOWN('?');

        companion object {
            fun fromChar(c: Char) = entries.first { it.c == c }
        }
    }

    @JvmInline
    value class Criteria(val lengths: List<Long>)

    companion object {
        fun of(line: String): ConditionRecord {
            val (condition, result) = line.split(" ")
            return ConditionRecord(
                row = Row(condition.map { ConditionType.fromChar(it) }),
                criteria = Criteria(result.split(",").map { it.toLong() })
            )
        }
    }
}

fun <E> List<E>.repeatList(n: Int): List<E> = sequence {
    repeat(n) {
        yieldAll(this@repeatList)
    }
}.toList()

fun <E> List<E>.repeatList(n: Int, separator: E): List<E> = sequence {
    repeat(n) {
        yieldAll(this@repeatList)
        if (it < n - 1L) yield(separator)
    }
}.toList()
