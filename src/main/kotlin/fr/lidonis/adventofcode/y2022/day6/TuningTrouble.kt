package fr.lidonis.adventofcode.y2022.day6

class TuningTrouble(val input: String) {

    fun firstChar(size: Int): Int {
        for (i in 0..input.length - size) {
            if (input.substring(i, i + size).toSet().size == size) {
                return i + size
            }
        }
        error("not found")
    }

}
