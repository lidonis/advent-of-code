package fr.lidonis.adventofcode.y2019.intcodecomputer

object IntCodeComputerFactory {

    fun buildBasicComputer(program: String) = BasicIntCodeComputer(toLongs(program))

    fun buildIOComputer(program: String) = IntCodeComputer(toLongs(program))

    fun buildASCIIComputer(program: String) = IntCodeComputer(toLongs(program))

    private fun toLongs(program: String) = program.split(",").map(String::toLong)

}