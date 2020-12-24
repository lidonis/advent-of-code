package fr.lidonis.adventofcode

import fr.lidonis.adventofcode.common.Answer
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.streams.asStream
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.reflections.Reflections

class NonRegressionTest {

    @TestFactory
    fun `Test all Advent of Code`() = sequence {
        AdventOfCode::class.java.let {
            for (clazz in Reflections(it.packageName).getSubTypesOf(it)) {
                buildAdventCodeTest(clazz)
            }
        }
    }.asStream()

    private suspend fun SequenceScope<DynamicTest>.buildAdventCodeTest(clazz: Class<out AdventOfCode>) {
        clazz.kotlin.objectInstance?.let { adventOfCode ->
            addPart(adventOfCode, 1)
            addPart(adventOfCode, 2)
        }
    }

    private suspend fun SequenceScope<DynamicTest>.addPart(adventOfCode: AdventOfCode, partId: Int) {
        partFunction(adventOfCode, partId)?.let { partFunction ->
            getAnswer(partFunction).let { answer ->
                val description = "AoC ${adventOfCode.year} day ${adventOfCode.day} part $partId"
                yield(DynamicTest.dynamicTest(description) {
                    assertThat(partFunction.call(adventOfCode).toString()).isEqualTo(answer).describedAs(description)
                })
            }
        }
    }

    private fun partFunction(adventOfCode: AdventOfCode, partId: Int) =
        adventOfCode.javaClass.kotlin.declaredMemberFunctions.find { it.name == "part$partId" }

    private fun getAnswer(partMethod: KFunction<*>): String {
        return partMethod.findAnnotation<Answer>()?.answer ?: "Answer not found"
    }
}
