package fr.lidonis.adventofcode.y2019.day7

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

class Amplifiers(private val program: List<Long>, phaseSettings: List<Long>) {

    private val amplifiers = phaseSettings.map {
        val computer = IntCodeComputer(program)
        computer.input(it)
        computer
    }.asSequence().cycle()

    fun run(): Long {
        var signal = 0L
        for(amplifier in amplifiers.iterator()){
            amplifier.input(signal)
            signal = amplifier.nextOutput()?: signal
        }
        return signal
    }
}

fun <T> Sequence<T>.cycle(): Sequence<T> =
        generateSequence(this) { this }.flatten()