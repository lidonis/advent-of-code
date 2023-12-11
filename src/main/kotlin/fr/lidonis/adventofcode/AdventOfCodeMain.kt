package fr.lidonis.adventofcode

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.required
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val parser = ArgParser("example")
    val year by parser.option(
        type = ArgType.Int,
        shortName = "y",
        fullName = "year",
        description = "Advent of Code year"
    ).required()
    val day by parser.option(
        type = ArgType.Int,
        shortName = "d",
        fullName = "day",
        description = "Advent of Code day"
    ).required()
    parser.parse(args)
    val exitCode = AdventOfCodeCommand(year, day).call()
    exitProcess(exitCode)
}
