package fr.lidonis.adventofcode.y2019.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ImageDecoderTest {

    @Test
    fun checksum() {
        val decoder = ImageDecoder("0222112222120000", 2, 2)
        assertThat(decoder.checksum()).isEqualTo(4)
    }

    @Test
    fun `decode image`() {
        val decoder = ImageDecoder("0222112222120000", 2, 2)
        assertThat(decoder.decode()).containsExactly("01", "10")
    }
}