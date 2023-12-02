package fr.lidonis.adventofcode.y2021.day6

private const val MAX_TIMER = 8
private const val RESET_TIMER = 6
private const val REPRODUCE_TIMER = 0

class Lanternfish(text: String) {

    private val initialState = LongArray(MAX_TIMER + 1).apply {
        for (fish in text.splitToSequence(',')) {
            this[fish.toInt()]++
        }
    }

    fun reproduce(days: Int): Long {
        val state = initialState.copyOf()
        repeat(days) {
            val reproduce = state[REPRODUCE_TIMER]
            state.copyInto(state, 0, 1)
            state[RESET_TIMER] += reproduce
            state[MAX_TIMER] = reproduce
        }
        return state.sum()
    }
}
