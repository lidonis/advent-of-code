package fr.lidonis.adventofcode.y2019

import fr.lidonis.adventofcode.common.ResourceReader
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

private const val MAX_TRIES = 10

fun main() {
    val program = ResourceReader("/input/y2019/day25.txt")?.text() ?: error("Input not found")
    val computer = IntCodeComputerFactory.buildASCIIComputer(program)
    while (true) {
        computer.tryNextOutputChar(MAX_TRIES)?.let {
            print(it, computer)
        }
    }
}

private fun print(it: Char, computer: IntCodeComputer) {
    print(it)
    if (it == '?') {
        readlnOrNull()?.run {
            computer.input(this)
        }
    }
}
