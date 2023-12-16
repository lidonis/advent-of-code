package fr.lidonis.adventofcode.common.aoc

import fr.lidonis.adventofcode.AdventOfCode
import fr.lidonis.adventofcode.common.Answer
import fr.lidonis.adventofcode.common.reflect.getAllSubclasses
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DynamicTest
import kotlin.reflect.KFunction
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.findAnnotation
import kotlin.streams.asStream

object AocTestFactory {

    inline fun <reified T : AdventOfCode> adventOfCodeTest() = sequence {
        for (clazz in getAllSubclasses<T>()) {
            buildAdventCodeTest(clazz)
        }
    }.asStream()

    suspend fun SequenceScope<DynamicTest>.buildAdventCodeTest(clazz: Class<out AdventOfCode>) {
        clazz.kotlin.objectInstance?.let { adventOfCode ->
            addPart(adventOfCode, 1)
            addPart(adventOfCode, 2)
        }
    }

    private suspend fun SequenceScope<DynamicTest>.addPart(adventOfCode: AdventOfCode, partId: Int) {
        partFunction(adventOfCode, partId)?.let { partFunction ->
            getAnswer(partFunction).let { answer ->
                val description = "day ${adventOfCode.day} part $partId"
                yield(
                    DynamicTest.dynamicTest(description) {
                        assertThat(
                            partFunction.call(adventOfCode).toString()
                        ).isEqualTo(answer).describedAs(description)
                    }
                )
            }
        }
    }

    private fun partFunction(adventOfCode: AdventOfCode, partId: Int) =
        adventOfCode.javaClass.kotlin.declaredMemberFunctions.find { it.name == "part$partId" }

    private fun getAnswer(partMethod: KFunction<*>): String {
        return partMethod.findAnnotation<Answer>()?.answer ?: "Answer not found"
    }
}