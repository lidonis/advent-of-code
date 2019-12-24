import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day24Test {

    @Test
    fun `biodiversity rating`() {
        val eris = Eris(
            ".....\n" +
                    ".....\n" +
                    ".....\n" +
                    "#....\n" +
                    ".#..."
        )
        assertThat(eris.biodiversityRating).isEqualTo(2129920)
    }
}