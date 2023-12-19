package fr.lidonis.adventofcode.y2023.day19

private val PART_REGEX = """\{x=(\d+),m=(\d+),a=(\d+),s=(\d+)}""".toRegex()
private val GROUP_REGEX = """(\w+)\{([^{}]+)}""".toRegex()

class Aplenty(private val input: String) {

    private val groups = input.split("\n\n")
    private val rules = groups[0].lines().map(Rule.Companion::fromString)
    private val parts = groups[1].lines().map(Part.Companion::fromString)

    fun part1() =
       parts.filter { part -> rules.validate(part) }.sumOf { it.score }

    private fun List<Rule>.validate(part: Part): Boolean {
        var ruleName = "in"
        while (ruleName != "R" && ruleName != "A") {
            println("$part -> $ruleName")
            ruleName = first { it.name == ruleName }.validate(part) ?: return false
        }
        println("$part -> $ruleName")
        return ruleName == "A"
    }

    fun part2() = input.length

    data class Rule(val name: String, val parts: List<Step>){
        fun validate(part: Part): String? {
            parts.forEach { step ->
                when (step) {
                    is Step.ConditionalStep -> {
                        val value = when (step.part) {
                            "x" -> part.x
                            "m" -> part.m
                            "a" -> part.a
                            "s" -> part.s
                            else -> error("Invalid part: ${step.part}")
                        }
                        if (!step.compare(value, step.value)) return step.next
                    }
                    is Step.FixedStep -> return step.next
                }
            }
            return null
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
        val next: String

        data class ConditionalStep(
            val part: String, val compare: Char, val value: Int, override val next: String
        ) : Step {
            fun compare(value: Int, value1: Int): Boolean {
                return when (compare) {
                    '>' -> value < value1
                    '<' -> value > value1
                    else -> error("Invalid compare: $compare")
                }
            }
        }

        data class FixedStep(override val next: String) : Step

        companion object {
            fun fromString(s: String): Step {
                val match = """(\w+)([<>])(\d+):(\w+)""".toRegex().matchEntire(s) ?: return FixedStep(s)
                val (part, compare, value, next) = match.destructured
                return ConditionalStep(part, compare.first(), value.toInt(), next)
            }
        }
    }

    data class Part(val x: Int, val m: Int, val a: Int, val s: Int){
        val score by lazy { x + m + a + s }

        companion object {
            @Suppress("DestructuringDeclarationWithTooManyEntries")
            fun fromString(s: String): Part {
                val match = PART_REGEX.matchEntire(s) ?: error("Invalid part: $s")
                val (x, m, a, s) = match.destructured
                return Part(x.toInt(), m.toInt(), a.toInt(), s.toInt())
            }
        }
    }
}
