package fr.lidonis.adventofcode.y2019.intcodecomputer

object IntCodeComputerFactory {

    fun buildBasicComputer(program: List<Long>): CodeComputer {
        return BasicIntCodeComputer(program)
    }

    fun buildIOComputer(program: List<Long>): IOCodeComputer {
        return IntCodeComputer(program)
    }

    fun buildASCIIComputer(program: List<Long>): ASCIICodeComputer {
        return IntCodeComputer(program)
    }

}