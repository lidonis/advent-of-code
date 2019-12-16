import org.junit.Assert
import org.junit.Test

internal class Day16Test {

    @Test
    fun `12345678 phase is 48226158`() {
        val phased = FlawedFrequencyTransmission.phase(listOf(1, 2, 3, 4, 5, 6, 7, 8))
        Assert.assertEquals(listOf(4L, 8, 2, 2, 6, 1, 5, 8), phased)
    }

    @Test
    fun `48226158 phase is 34040438`() {
        val phased = FlawedFrequencyTransmission.phase(listOf(4, 8, 2, 2, 6, 1, 5, 8))
        Assert.assertEquals(listOf(3L, 4, 0, 4, 0, 4, 3, 8), phased)
    }

    @Test
    fun `34040438 phase is 03415518`() {
        val phased = FlawedFrequencyTransmission.phase(listOf(3, 4, 0, 4, 0, 4, 3, 8))
        Assert.assertEquals(listOf(0L, 3, 4, 1, 5, 5, 1, 8), phased)
    }

    @Test
    fun `03415518 phase is 01029498`() {
        val phased = FlawedFrequencyTransmission.phase(listOf(0, 3, 4, 1, 5, 5, 1, 8))
        Assert.assertEquals(listOf(0L, 1, 0, 2, 9, 4, 9, 8), phased)
    }

    @Test
    fun `12345678 phased 4 times is 01029498`() {
        val phased = FlawedFrequencyTransmission.phases(listOf(1, 2, 3, 4, 5, 6, 7, 8), 4)
        Assert.assertEquals(listOf(0L, 1, 0, 2, 9, 4, 9, 8), phased)
    }

    @Test
    fun `80871224585914546619083218645595 phased 100 times first 8 digits are 24176176`() {
        val phased = FlawedFrequencyTransmission.phases(
            listOf(8, 0, 8, 7, 1, 2, 2, 4, 5, 8, 5, 9, 1, 4, 5, 4, 6, 6, 1, 9, 0, 8, 3, 2, 1, 8, 6, 4, 5, 5, 9, 5), 100
        )
        Assert.assertEquals(listOf(2L, 4, 1, 7, 6, 1, 7, 6), phased.take(8))
    }

    @Test
    fun `19617804207202209144916044189917 phased 100 times first 8 digits are 73745418`() {
        val phased = FlawedFrequencyTransmission.phases(
            listOf(1, 9, 6, 1, 7, 8, 0, 4, 2, 0, 7, 2, 0, 2, 2, 0, 9, 1, 4, 4, 9, 1, 6, 0, 4, 4, 1, 8, 9, 9, 1, 7), 100
        )
        Assert.assertEquals(listOf(7L, 3, 7, 4, 5, 4, 1, 8), phased.take(8))
    }

    @Test
    fun `69317163492948606335995924319873 phased 100 times first 8 digits are 52432133`() {
        val phased = FlawedFrequencyTransmission.phases(
            listOf(6, 9, 3, 1, 7, 1, 6, 3, 4, 9, 2, 9, 4, 8, 6, 0, 6, 3, 3, 5, 9, 9, 5, 9, 2, 4, 3, 1, 9, 8, 7, 3), 100
        )
        Assert.assertEquals(listOf(5L, 2, 4, 3, 2, 1, 3, 3), phased.take(8))
    }
}