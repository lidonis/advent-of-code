package fr.lidonis.adventofcode.y2019.intcodecomputer

import java.util.Deque

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

    fun input(value: String) {
        (value + "\n").chars().forEach { input(it.toLong()) }
    }

    fun tryNextOutputChar(maxTries: Int) = tryNextOutput(maxTries)?.toInt()?.toChar()
}

interface OutputDevice {
    val values: Deque<Long>
    fun write(value: Long)
    fun reset()
}

interface InputDevice {
    fun read(): Long
    fun add(value: Long)
    fun reset()
}
