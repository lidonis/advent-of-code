package fr.lidonis.adventofcode.y2021.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PacketDecoderTest {

    private val input = """
    """.trimIndent()

    @Test
    fun part1() {
        val packetDecoder = PacketDecoder(input)
        val result = packetDecoder.part1()
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun part2() {
        val packetDecoder = PacketDecoder(input)
        val result = packetDecoder.part2()
        assertThat(result).isEqualTo(0)
    }
}
