import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Day8Test {

    @Test
    fun checksum() {
        val decoder = ImageDecoder("0222112222120000", 2, 2)
        Assertions.assertEquals(4, decoder.checksum())    }

    @Test
    fun `decode image`() {
        val decoder = ImageDecoder("0222112222120000", 2, 2)
        Assertions.assertEquals(listOf("01","10"), decoder.decode())
    }
}