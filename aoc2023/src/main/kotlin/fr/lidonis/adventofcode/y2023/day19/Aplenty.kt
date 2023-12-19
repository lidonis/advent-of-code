package fr.lidonis.adventofcode.y2023.day19

import fr.lidonis.adventofcode.common.multiply

private val PART_REGEX = """\{x=(\d+),m=(\d+),a=(\d+),s=(\d+)}""".toRegex()
private val GROUP_REGEX = """(\w+)\{([^{}]+)}""".toRegex()

private const val INITIAL_RULE_NAME = "in"
private const val ACCEPTED_RULE_NAME = "A"
private const val REJECTED_RULE_NAME = "R"

private const val MIN_RATING = 1
private const val MAX_RATING = 4000

class Aplenty(input: String) {

    private val groups = input.split("\n\n")
    private val rules = groups[0].lines().map(Rule.Companion::fromString).associateBy { it.name }
    private val partRatings = groups[1].lines().map(PartRating.Companion::fromString)

    fun part1() =
        partRatings
            .asSequence()
            .filter { part -> rules.validate(part) }
            .sumOf { it.score }

    private fun Map<String, Rule>.validate(partRating: PartRating): Boolean {
        var ruleName = INITIAL_RULE_NAME
        do {
            ruleName = get(ruleName)?.validate(partRating) ?: error("Invalid rule: $ruleName")
        } while (ruleName != ACCEPTED_RULE_NAME && ruleName != REJECTED_RULE_NAME)
        return ruleName == ACCEPTED_RULE_NAME
    }

    fun part2() =
        rules
            .validate(PartRatingRange.INITIAL)
            .sumOf { it.size() }

    private fun Map<String, Rule>.validate(partRating: PartRatingRange) =
        buildList {
            val workflow = ArrayDeque<Pair<String, PartRatingRange>>()
            workflow.add(INITIAL_RULE_NAME to partRating)
            while (workflow.isNotEmpty()) {
                val range = workflow.removeFirst()
                val rule = get(range.first) ?: error("Invalid rule: ${range.first}")
                val notRejected = rule.validate(range.second).filter { it.first != "R" }
                val (accepted, toContinue) = notRejected.partition { it.first == ACCEPTED_RULE_NAME }
                addAll(accepted.map { it.second })
                workflow.addAll(toContinue)
            }
        }

    data class Rule(val name: String, val parts: List<Step>) {
        fun validate(partRating: PartRating): String {
            parts.forEach { step ->
                val next = step.next(partRating)
                if (next != null) return next
            }
            error("Invalid rule: $this")
        }

        fun validate(partRating: PartRatingRange) =
            buildList {
                var p = partRating
                parts.forEach { step ->
                    val next = step.next(p)
                    if (next.accepted != null) add(next.accepted)
                    p = next.rejected ?: return@forEach
                }
            }

        companion object {
            fun fromString(s: String): Rule {
                val match = GROUP_REGEX.matchEntire(s) ?: error("Invalid rule: $s")
                val (name, steps) = match.destructured
                return Rule(name, steps.split(",").map { Step.fromString(it) })
            }
        }
    }

    sealed interface Step {
        fun next(partRating: PartRating): String?
        fun next(partRatingRange: PartRatingRange): RangeStepResult

        data class ConditionalStep(
            val category: String, val compare: Compare, val value: Int, val next: String
        ) : Step {

            override fun next(partRating: PartRating): String? {
                val value = partRating.map[category] ?: error("Invalid part: $category")
                return next.takeIf { compare(value, this.value) }
            }

            override fun next(partRatingRange: PartRatingRange): RangeStepResult {
                val range = partRatingRange.map[category] ?: error("Invalid part: $category")
                return when (compare) {
                    Compare.LESS_THAN -> {
                        val (downRange, upRange) = range.split(value - 1)
                        RangeStepResult(next, partRatingRange, category, downRange, upRange)
                    }

                    Compare.GREATER_THAN -> {
                        val (downRange, upRange) = range.split(value)
                        RangeStepResult(next, partRatingRange, category, upRange, downRange)
                    }

                }
            }

            private fun IntRange.split(value: Int) = (first..value) to (value + 1..last)

            private fun compare(value: Int, value1: Int): Boolean {
                return when (compare) {
                    Compare.LESS_THAN -> value < value1
                    Compare.GREATER_THAN -> value > value1
                }
            }

            enum class Compare {
                LESS_THAN,
                GREATER_THAN;

                companion object {
                    fun fromString(s: String) = when (s) {
                        "<" -> LESS_THAN
                        ">" -> GREATER_THAN
                        else -> error("Invalid compare: $s")
                    }
                }
            }
        }

        data class FixedStep(val next: String) : Step {
            override fun next(partRating: PartRating) = next

            override fun next(partRatingRange: PartRatingRange) =
                RangeStepResult(next to partRatingRange, null)
        }

        data class RangeStepResult(val accepted: Pair<String, PartRatingRange>?, val rejected: PartRatingRange?) {
            constructor(
                ruleName: String,
                partRatingRange: PartRatingRange,
                category: String,
                acceptedRange: IntRange,
                rejectedRange: IntRange,
            ) : this(
                accepted = acceptedRange.takeIf { !it.isEmpty() }
                    ?.let { ruleName to partRatingRange.copy(category to acceptedRange) },
                rejected = rejectedRange.takeIf { !it.isEmpty() }
                    ?.let { partRatingRange.copy(category to rejectedRange) }
            )
        }

        companion object {
            @Suppress("DestructuringDeclarationWithTooManyEntries")
            fun fromString(s: String): Step {
                val match = """(\w+)([<>])(\d+):(\w+)""".toRegex().matchEntire(s) ?: return FixedStep(s)
                val (part, compare, value, next) = match.destructured
                return ConditionalStep(part, ConditionalStep.Compare.fromString(compare), value.toInt(), next)
            }
        }
    }

    class PartRating private constructor(val map: Map<String, Int>) {
        private val x: Int by map
        private val m: Int by map
        private val a: Int by map
        private val s: Int by map

        constructor(x: Int, m: Int, a: Int, s: Int) : this(mapOf("x" to x, "m" to m, "a" to a, "s" to s))

        val score by lazy { map.values.sum() }

        override fun toString(): String {
            return "PartRating(x=$x, m=$m, a=$a, s=$s, score=$score)"
        }

        companion object {
            @Suppress("DestructuringDeclarationWithTooManyEntries")
            fun fromString(input: String): PartRating {
                val match = PART_REGEX.matchEntire(input) ?: error("Invalid part rating: $input")
                val (x, m, a, s) = match.destructured
                return PartRating(x.toInt(), m.toInt(), a.toInt(), s.toInt())
            }
        }

    }

    class PartRatingRange private constructor(val map: Map<String, IntRange>) {
        private val x: IntRange by map
        private val m: IntRange by map
        private val a: IntRange by map
        private val s: IntRange by map

        constructor(x: IntRange, m: IntRange, a: IntRange, s: IntRange) :
            this(mapOf("x" to x, "m" to m, "a" to a, "s" to s))

        fun size() = map.values.map { it.size().toLong() }.multiply()

        private fun IntRange.size() = last - first + 1

        fun copy(vararg add: Pair<String, IntRange>) = PartRatingRange(
            buildMap {
                putAll(map)
                putAll(add.toMap())
            })

        override fun toString(): String {
            return "PartRatingRange(x=$x, m=$m, a=$a, s=$s)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as PartRatingRange

            return map == other.map
        }

        override fun hashCode() = map.hashCode()

        companion object {
            val INITIAL = PartRatingRange(
                x = MIN_RATING..MAX_RATING,
                m = MIN_RATING..MAX_RATING,
                a = MIN_RATING..MAX_RATING,
                s = MIN_RATING..MAX_RATING
            )
        }

    }
}
