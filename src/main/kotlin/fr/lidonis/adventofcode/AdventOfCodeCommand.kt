package fr.lidonis.adventofcode

import org.fusesource.jansi.Ansi.Color.BLACK
import org.fusesource.jansi.Ansi.Color.GREEN
import org.fusesource.jansi.Ansi.ansi
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class AdventOfCodeCommand(private val year: Int, private val day: Int) {

    @ExperimentalTime
    fun call(): Int {
        val (adventOfCode, loadTime) = measureTimedValue {
            load(year, day)
        }
        val (part1, part1Time) = measureTimedValue {
            adventOfCode.part1()
        }
        val (part2, part2Time) = measureTimedValue {
            adventOfCode.part2()
        }
        println(
            ansi().eraseScreen().bg(BLACK).fg(GREEN).a(
                """
            *** Advent of Code $year day $day ***
            Part 1 answer : $part1
            Part 2 answer : $part2
                        Timing
            Loading took $loadTime
            Part 1 took $part1Time
            Part 2 took $part2Time
            Total took ${loadTime + part1Time + part2Time}
                """.trimIndent()
            )
        )
        return 0
    }

    private fun load(year: Int, day: Int) = try {
        Class.forName("fr.lidonis.adventofcode.y$year.day$day.Day$day").getDeclaredField("INSTANCE")
            .get(null) as AdventOfCode
    } catch (e: ClassNotFoundException) {
        throw IllegalStateException("Advent of code not found with params year: $year day: $day", e)
    } catch (e: ClassCastException) {
        throw IllegalStateException("Class found but not Advent of code object", e)
    }
}
