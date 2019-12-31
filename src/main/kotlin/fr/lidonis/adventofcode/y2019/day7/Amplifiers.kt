package fr.lidonis.adventofcode.y2019.day7

import fr.lidonis.adventofcode.y2019.intcodecomputer.IOCodeComputer
import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

class Amplifiers(private val program: List<Long>, phaseSettings: List<Long>) {

    private val amplifiers: Sequence<IOCodeComputer> = phaseSettings.map {
        IntCodeComputerFactory.buildIOComputer(program).apply {
            input(it)
        }
    }.asSequence().cycle()

    fun run() = amplifiers.fold(0L) { signal, amplifier ->
        amplifier.input(signal)
        amplifier.nextOutput() ?: signal
    }
}

fun <T : Iterator<T>> Sequence<T>.cycle(): Sequence<T> =
    generateSequence {
        if (this.all { it.hasNext() }) {
            this
        } else {
            null
        }
    }.flatten()