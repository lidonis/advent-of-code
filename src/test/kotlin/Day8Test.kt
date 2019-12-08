import org.junit.Assert
import org.junit.Test

internal class Day8Test {

    @Test
    fun checksum() {
        val decoder = ImageDecoder("0222112222120000", 2, 2)
        Assert.assertEquals(4, decoder.checksum())    }

    @Test
    fun `decode image`() {
        val decoder = ImageDecoder("0222112222120000", 2, 2)
        Assert.assertEquals(listOf("01","10"), decoder.decode())
    }
}