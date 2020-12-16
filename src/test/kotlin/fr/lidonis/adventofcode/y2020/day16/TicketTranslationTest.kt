package fr.lidonis.adventofcode.y2020.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class TicketTranslationTest {

    @Test
    fun scanningErrorRate() {
        val scan = """
            class: 1-3 or 5-7
            row: 6-11 or 33-44
            seat: 13-40 or 45-50

            your ticket:
            7,1,14

            nearby tickets:
            7,3,47
            40,4,50
            55,2,20
            38,6,12
        """.trimIndent().lines()
        assertThat(TicketTranslation(scan).scanningErrorRate()).isEqualTo(71)
    }

    @Test
    internal fun ticket() {
        val scan = """
            class: 0-1 or 4-19
            row: 0-5 or 8-19
            seat: 0-13 or 16-19

            your ticket:
            11,12,13

            nearby tickets:
            3,9,18
            15,1,5
            5,14,9
        """.trimIndent().lines()
        val ticket = mapOf("class" to 12, "row" to 11, "seat" to 13)
        assertThat(TicketTranslation(scan).ticket()).isEqualTo(ticket)
    }
}