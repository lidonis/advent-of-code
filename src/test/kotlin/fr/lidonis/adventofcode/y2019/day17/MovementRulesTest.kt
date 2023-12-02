package fr.lidonis.adventofcode.y2019.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MovementRulesTest {

    @Test
    fun compressSimple() {
        val program = "R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2"
        val movementRules = MovementRules.from(program.split(","))
        assertThat(movementRules.decompress()).isEqualTo(program)
        val (mainRoutine, functionA, functionB, functionC) = movementRules
        assertThat(mainRoutine.length).isLessThan(20)
        assertThat(functionA.length).isLessThan(20)
        assertThat(functionB.length).isLessThan(20)
        assertThat(functionC.length).isLessThan(20)
    }

    @Test
    fun compressLong() {
        val program =
            "R,4,R,10,R,8,R,4,R,10,R,6,R,4,R,4,R,10,R,8,R,4,R,10,R,6,R,4,R,4,L,12,R,6,L,12,R,10,R,6,R,4,R,4,L," +
                "12,R,6,L,12,R,4,R,10,R,8,R,4,R,10,R,6,R,4,R,4,L,12,R,6,L,12"
        val movementRules = MovementRules.from(program.split(","))
        assertThat(movementRules.decompress()).isEqualTo(program)
        val (mainRoutine, functionA, functionB, functionC) = movementRules
        assertThat(mainRoutine.length).isLessThan(20)
        assertThat(functionA.length).isLessThan(20)
        assertThat(functionB.length).isLessThan(20)
        assertThat(functionC.length).isLessThan(20)
    }
}
