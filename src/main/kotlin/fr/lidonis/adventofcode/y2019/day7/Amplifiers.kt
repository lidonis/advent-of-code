package fr.lidonis.adventofcode.y2019.day7

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputer

class Amplifiers(private val program: List<Long>, phaseSettings: List<Long>) {

    private val amplifiers = phaseSettings.map {
        val computer = IntCodeComputer(program)
        computer.input(it)
        computer
    }

    fun run(): Long {
        var signal = 0L
        var currentAmplifier = 0
        while (amplifiers[currentAmplifier].hasNext()) {
            val amplifier = amplifiers[currentAmplifier]
            amplifier.input(signal)
            signal = amplifier.nextOutput()?: signal
            if (amplifier == amplifiers.last()) {
                currentAmplifier = 0
            } else {
                currentAmplifier++
            }
        }
        return signal
    }
}
