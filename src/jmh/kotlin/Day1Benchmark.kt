package fr.lidonis.adventofcode.y2019.day1

import fr.lidonis.adventofcode.common.AdventOfCode
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@State(Scope.Thread)
open class Day1Benchmark {

    private lateinit var aoc: AdventOfCode

    @Setup
    fun prepare() {
        aoc = Day1
    }

    @Benchmark
    fun part1() = aoc.part1()

    @Benchmark
    fun part2() = aoc.part2()
}
