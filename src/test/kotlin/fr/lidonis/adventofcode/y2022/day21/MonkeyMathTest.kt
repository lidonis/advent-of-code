package fr.lidonis.adventofcode.y2022.day21

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MonkeyMathTest {
    val lines = """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32
        """.trimIndent().lines()

    @Test
    fun `root yell`() {
        val result = MonkeyMath(lines).root()

        assertThat(result).isEqualTo(152)
    }

    @Test
    fun `human yell`() {
        val result = MonkeyMath(lines).human()

        assertThat(result).isEqualTo(301)
    }
}
