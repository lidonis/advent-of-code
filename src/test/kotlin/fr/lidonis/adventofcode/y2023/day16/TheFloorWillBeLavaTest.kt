package fr.lidonis.adventofcode.y2023.day16

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class TheFloorWillBeLavaTest {

    private val input = """
        .|...\....
        |.-.\.....
        .....|-...
        ........|.
        ..........
        .........\
        ..../.\\..
        .-.-/..|..
        .|....-|.\
        ..//.|....
    """.trimIndent()

    @Test
    fun part1() {
        val theFloorWillBeLava = TheFloorWillBeLava(input.lines())
        val result = theFloorWillBeLava.part1()
        assertThat(result).isEqualTo(46)
    }

    @ParameterizedTest
    @ArgumentsSource(ContraptionLayoutProvider::class)
    fun part1WithContraptionLayout(layout: String, expected: Int) {
        val theFloorWillBeLava = TheFloorWillBeLava(layout.lines())
        val result = theFloorWillBeLava.part1()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun part2() {
        val theFloorWillBeLava = TheFloorWillBeLava(input.lines())
        val result = theFloorWillBeLava.part2()
        assertThat(result).isEqualTo(51)
    }
}

private class ContraptionLayoutProvider: ArgumentsProvider {
    override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> = Stream.of(
        Arguments.of(
            """
                .-.
                |..
            """.trimIndent(),
            1
        ),
        Arguments.of(
            """
                .|.
                --.
            """.trimIndent(),
            3
        ),
        Arguments.of(
            """
                .\.
                -/.
            """.trimIndent(),
            3
        ),
    )
}
