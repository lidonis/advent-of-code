package fr.lidonis.adventofcode.y2019.day7

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

class Amplifiers(private val program: List<Long>, phaseSettings: List<Long>) {

    private val amplifiers = phaseSettings.map {
        val computer = IntCodeComputer(program)
        computer.input(it)
        computer
    }.asSequence()

    fun run() = amplifiers.fold(0L) { signal, amplifier ->
        amplifier.input(signal)
        amplifier.nextOutput() ?: signal
    }
}

fun <T> Sequence<T>.cycle(): Sequence<T> =
        generateSequence { this }.flatten()