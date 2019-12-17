import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

internal class PositionTest {

    @Test
    fun `test north turn right is east`() {
        assertThat(CardinalPoint.NORTH.turnRight()).isEqualTo(CardinalPoint.EAST)
    }

    @Test
    fun `test east turn right is south`() {
        assertThat(CardinalPoint.EAST.turnRight()).isEqualTo(CardinalPoint.SOUTH)
    }

    @Test
    fun `test south turn right is west`() {
        assertThat(CardinalPoint.SOUTH.turnRight()).isEqualTo(CardinalPoint.WEST)
    }


    @Test
    fun `test west turn right is north`() {
        assertThat(CardinalPoint.WEST.turnRight()).isEqualTo(CardinalPoint.NORTH)
    }


    @Test
    fun `test north turn left is west`() {
        assertThat(CardinalPoint.NORTH.turnLeft()).isEqualTo(CardinalPoint.WEST)
    }

    @Test
    fun `test west turn left is south`() {
        assertThat(CardinalPoint.WEST.turnLeft()).isEqualTo(CardinalPoint.SOUTH)
    }

    @Test
    fun `test south turn left is east`() {
        assertThat(CardinalPoint.SOUTH.turnLeft()).isEqualTo(CardinalPoint.EAST)
    }


    @Test
    fun `test east turn left is north`() {
        assertThat(CardinalPoint.EAST.turnLeft()).isEqualTo(CardinalPoint.NORTH)
    }

}