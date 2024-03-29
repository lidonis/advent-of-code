package fr.lidonis.adventofcode.y2022.day6

class TuningTrouble(val input: String) {

    fun startOfMessage(size: Int) =
        input.windowed(size).indexOfFirst { it.toSet().size == size } + size
}
