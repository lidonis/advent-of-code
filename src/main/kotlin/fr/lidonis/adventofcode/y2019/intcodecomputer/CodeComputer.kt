package fr.lidonis.adventofcode.y2019.intcodecomputer

import java.util.*

interface CodeComputer {
    val program: Iterable<Long>
    val memory: MutableList<Long>

    fun reset()

    fun run()
    fun hasNext(): Boolean
    fun next(): CodeComputer
}

interface IOCodeComputer : CodeComputer, Iterator<IOCodeComputer> {
    val outputs: Deque<Long>
    fun input(value: Long)
    fun nextOutput(): Long?
    fun tryNextOutput(maxTries: Int): Long?
}

interface ASCIICodeComputer : IOCodeComputer {
    fun input(value: String)
    fun tryNextOutputChar(maxTries: Int): Char?

}