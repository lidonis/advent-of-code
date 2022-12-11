package fr.lidonis.adventofcode.y2022.day11

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MonkeyInTheMiddleTest {

    private val input = """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
    """.trimIndent()
    @Test
    fun `monkey business`() {
        val monkeyBusiness = MonkeyInTheMiddle(input.lines()).monkeyBusiness()
        assertThat(monkeyBusiness).isEqualTo(10605)
    }

    @Test
    fun `monkey business no worries`() {
        val monkeyBusiness = MonkeyInTheMiddle(input.lines()).monkeyBusinessNoWorries()
        assertThat(monkeyBusiness).isEqualTo(2713310158)
    }
}
