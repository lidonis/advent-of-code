package fr.lidonis.adventofcode.y2019.day17

import java.util.ArrayDeque

private const val NB_FUNCTION = 3
private const val DELIMITER = ","
private const val CHAR_LIMIT = 20

data class MovementRules(val mainRoutine: String, val functionA: String, val functionB: String, val functionC: String) {

    fun decompress() = mainRoutine.split(DELIMITER).map {
        when (it) {
            "A" -> functionA
            "B" -> functionB
            "C" -> functionC
            else -> null
        }
    }.joinToString(DELIMITER)

    companion object {
        fun from(path: List<String>): MovementRules {
            val compressed = compress(path)
            compressed ?: error("Can't find compressed rules")
            return MovementRules(
                compressed.main.map { i -> 'A' + i }.joinToString(DELIMITER),
                compressed.functions[0].toString(),
                compressed.functions[1].toString(),
                compressed.functions[2].toString()
            )
        }

        private fun compress(path: List<String>): CompressState? {
            val deque = ArrayDeque<CompressState>()
            deque.push(CompressState())
            while (deque.isNotEmpty()) {
                val state = deque.pop()
                if (state.isSolution(path)) {
                    return state
                }
                val pathLeft = path.drop(state.index)
                deque.addAll(addToMain(state, pathLeft))
                if (state.canAddFunction()) {
                    deque.addAll(generateFunctions(pathLeft, state))
                }
            }
            return null
        }

        private fun addToMain(state: CompressState, pathLeft: List<String>) =
            state.functions.mapIndexedNotNull { i, function ->
                if (function == Function(pathLeft.take(function.size))) state.addToMain(i) else null
            }

        private fun generateFunctions(pathLeft: List<String>, state: CompressState) =
            pathLeft.indices.map { Function(pathLeft.take(it)) }
                .filter { it.charSize <= CHAR_LIMIT }
                .map { state.addFunction(it) }
    }
}

private data class CompressState(
    val main: List<Int> = emptyList(),
    val functions: List<Function> = emptyList(),
    val index: Int = 0
) {
    fun addFunction(function: Function) = CompressState(
        main + functions.size,
        functions.plusElement(function),
        index + function.size
    )

    fun addToMain(functionIndex: Int) = CompressState(
        main + functionIndex,
        functions,
        index + functions[functionIndex].size
    )

    fun canAddFunction() = functions.size < NB_FUNCTION

    fun isSolution(path: List<String>) = index == path.size
}

private inline class Function(val list: List<String>) {
    val size: Int
        get() = list.size

    val charSize get() = list.sumBy { it.length } + list.size - 1

    override fun toString() = list.joinToString(DELIMITER)
}
