package fr.lidonis.adventofcode.y2021.day2

import fr.lidonis.adventofcode.common.geo.plane.Position
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SubmarineCourseTest {

    private val example: List<String> = """
        forward 5
        down 5
        forward 8
        up 3
        down 8
        forward 2
    """.trimIndent().lines()

    @Test
    internal fun `planned course`() {
        val submarineCourse = SubmarineCourse(example)

        val plannedCourse = submarineCourse.plannedCourse()
        assertThat(plannedCourse).isEqualTo(Position(15, 10))
    }

    @Test
    internal fun `planned course with aim`() {
        val submarineCourse = SubmarineCourse(example)

        val plannedCourse = submarineCourse.plannedCourseWithAim()
        assertThat(plannedCourse).isEqualTo(Position(15, 60))
    }
}
