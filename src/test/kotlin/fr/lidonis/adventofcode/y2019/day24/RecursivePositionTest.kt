package fr.lidonis.adventofcode.y2019.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class RecursivePositionTest {

    @Test
    fun `position 3,3,0`() {
        assertThat(RecursivePosition(3, 3, 0).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(3, 2, 0),
            RecursivePosition(2, 3, 0),
            RecursivePosition(4, 3, 0),
            RecursivePosition(3, 4, 0)
        )
    }

    @Test
    fun `position 1,1,1`() {
        assertThat(RecursivePosition(1, 1, 1).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(1, 0, 1),
            RecursivePosition(0, 1, 1),
            RecursivePosition(2, 1, 1),
            RecursivePosition(1, 2, 1)
        )
    }

    @Test
    fun `position 0,3,1`() {
        assertThat(RecursivePosition(3, 0, 1).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(2, 1, 0),
            RecursivePosition(2, 0, 1),
            RecursivePosition(4, 0, 1),
            RecursivePosition(3, 1, 1)
        )
    }

    @Test
    fun `position 0,4,1`() {
        assertThat(RecursivePosition(4, 0, 1).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(2, 1, 0),
            RecursivePosition(3, 0, 1),
            RecursivePosition(3, 2, 0),
            RecursivePosition(4, 1, 1)
        )
    }

    @Test
    fun `position 2,3,0`() {
        assertThat(RecursivePosition(3, 2, 0).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(3, 1, 0),
            RecursivePosition(4, 0, 1),
            RecursivePosition(4, 1, 1),
            RecursivePosition(4, 2, 1),
            RecursivePosition(4, 3, 1),
            RecursivePosition(4, 4, 1),
            RecursivePosition(4, 2, 0),
            RecursivePosition(3, 3, 0)
        )
    }

    @Test
    fun `position 3,2,0`() {
        assertThat(RecursivePosition(2, 3, 0).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(x = 0, y = 4, z = 1),
            RecursivePosition(x = 1, y = 4, z = 1),
            RecursivePosition(x = 2, y = 4, z = 1),
            RecursivePosition(x = 3, y = 4, z = 1),
            RecursivePosition(x = 4, y = 4, z = 1),
            RecursivePosition(x = 1, y = 3, z = 0),
            RecursivePosition(x = 3, y = 3, z = 0),
            RecursivePosition(x = 2, y = 4, z = 0)
        )
    }

    @Test
    fun `position 1,0,1`() {
        assertThat(RecursivePosition(0, 1, 1).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(x = 0, y = 0, z = 1),
            RecursivePosition(x = 1, y = 2, z = 0),
            RecursivePosition(x = 1, y = 1, z = 1),
            RecursivePosition(x = 0, y = 2, z = 1)
        )
    }

    @Test
    fun `position 2,1,0`() {
        assertThat(RecursivePosition(1, 2, 0).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(x = 1, y = 1, z = 0),
            RecursivePosition(x = 0, y = 2, z = 0),
            RecursivePosition(x = 0, y = 0, z = 1),
            RecursivePosition(x = 0, y = 1, z = 1),
            RecursivePosition(x = 0, y = 2, z = 1),
            RecursivePosition(x = 0, y = 3, z = 1),
            RecursivePosition(x = 0, y = 4, z = 1),
            RecursivePosition(x = 1, y = 3, z = 0)
        )
    }

    @Test
    fun `position 4,2,1`() {
        assertThat(RecursivePosition(2, 4, 1).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(x = 2, y = 3, z = 1),
            RecursivePosition(x = 1, y = 4, z = 1),
            RecursivePosition(x = 3, y = 4, z = 1),
            RecursivePosition(x = 2, y = 3, z = 0)
        )
    }

    @Test
    fun `position 1,2,0`() {
        assertThat(RecursivePosition(2, 1, 0).neighbours()).containsExactlyInAnyOrder(
            RecursivePosition(x = 2, y = 0, z = 0),
            RecursivePosition(x = 1, y = 1, z = 0),
            RecursivePosition(x = 3, y = 1, z = 0),
            RecursivePosition(x = 0, y = 0, z = 1),
            RecursivePosition(x = 1, y = 0, z = 1),
            RecursivePosition(x = 2, y = 0, z = 1),
            RecursivePosition(x = 3, y = 0, z = 1),
            RecursivePosition(x = 4, y = 0, z = 1)
        )
    }
}
