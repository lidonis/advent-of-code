package fr.lidonis.adventofcode.y2019.day17

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MovementRulesTest {

    @Test
    fun `movement rule splitting`() {
        val rules = MovementRules.from("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2".split(","))
        assertThat(rules).isEqualTo(
            MovementRules(
                "A,B,C,B,A,C",
                "R,8,R,8",
                " R,4,R,4,R,8",
                "L,6,L,2"
            )
        )
    }
}