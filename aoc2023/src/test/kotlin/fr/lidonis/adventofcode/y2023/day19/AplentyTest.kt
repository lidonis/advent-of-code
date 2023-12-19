package fr.lidonis.adventofcode.y2023.day19

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class AplentyTest {

    private val input = """
        px{a<2006:qkq,m>2090:A,rfg}
        pv{a>1716:R,A}
        lnx{m>1548:A,A}
        rfg{s<537:gd,x>2440:R,A}
        qs{s>3448:A,lnx}
        qkq{x<1416:A,crn}
        crn{x>2662:A,R}
        in{s<1351:px,qqz}
        qqz{s>2770:qs,m<1801:hdj,R}
        gd{a>3333:R,R}
        hdj{m>838:A,pv}

        {x=787,m=2655,a=1222,s=2876}
        {x=1679,m=44,a=2067,s=496}
        {x=2036,m=264,a=79,s=2244}
        {x=2461,m=1339,a=466,s=291}
        {x=2127,m=1623,a=2188,s=1013}
    """.trimIndent()

    @Test
    fun part1() {
        val aplenty = Aplenty(input)
        val result = aplenty.part1()
        assertThat(result).isEqualTo(19114)
    }

    @Test
    fun part2() {
        val aplenty = Aplenty(input)
        val result = aplenty.part2()
        assertThat(result).isEqualTo(167409079868000)
    }

    @Nested
    inner class ConditionalStepTest {
        @Test
        fun `should return next rule for greater that`() {
            val step = Aplenty.Step.fromString("x>2000:qkq")
            val partRating = Aplenty.PartRatingRange.INITIAL
            val result = step.next(partRating)
            assertThat(result).isEqualTo(
                Aplenty.Step.RangeStepResult(
                    accepted = "qkq" to partRating.copy("x" to  2001..4000),
                    rejected = partRating.copy("x" to 1..2000),
                )
            )
        }

        @Test
        fun `should return next rule for less that`() {
            val step = Aplenty.Step.fromString("x<2000:qkq")
            val partRating = Aplenty.PartRatingRange.INITIAL
            val result = step.next(partRating)
            assertThat(result).isEqualTo(
                Aplenty.Step.RangeStepResult(
                    accepted = "qkq" to partRating.copy("x" to 1..1999),
                    rejected = partRating.copy("x" to  2000..4000),
                )
            )
        }

        @Test
        fun `should return next rule for fixed`() {
            val step = Aplenty.Step.fromString("qkq")
            val partRating = Aplenty.PartRatingRange.INITIAL
            val result = step.next(partRating)
            assertThat(result).isEqualTo(
                Aplenty.Step.RangeStepResult(
                    accepted = "qkq" to partRating,
                    rejected = null
                )
            )
        }
    }

    @Nested
    inner class RuleTest {
        @Test
        fun `should return new ranges`() {
            val rule = Aplenty.Rule.fromString("px{a<2006:qkq,m>2090:A,rfg}")
            val partRatingRange = Aplenty.PartRatingRange.INITIAL
            val result = rule.validate(partRatingRange)
            assertThat(result).containsExactly(
                "qkq" to partRatingRange.copy("a" to 1..2005),
                "A" to partRatingRange.copy("a" to 2006..4000,"m" to 2091..4000),
                "rfg" to partRatingRange.copy("a" to  2006..4000, "m" to 1..2090),
            )
        }
    }
}
