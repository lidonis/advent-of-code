package fr.lidonis.adventofcode.y2019.day17

data class MovementRules(val mainRoutine: String, val functionA: String, val functionB: String, val functionC: String) {

    companion object {
        fun from(path: List<String>): MovementRules {
            val compressed = compress(path)
            compressed ?: error("Can't find compressed rules")
            println(compressed)
            return MovementRules(
                compressed.main.map { i -> 'A' + i }.joinToString(","),
                compressed.programs[0].joinToString(","),
                compressed.programs[1].joinToString(","),
                compressed.programs[2].joinToString(",")
            )
        }

        private fun compress(path: List<String>): CompressState? {
            val stack = mutableListOf(CompressState(emptyList(), emptyList(), 0))
            while (stack.isNotEmpty()) {
                val state = stack.removeAt(stack.lastIndex)
                if (state.index >= path.size) {
                    return state
                }
                val (main, programs, index) = state
                if (main.size < 10) {
                    for ((i, program) in programs.withIndex()) {
                        if (path.subList(index, minOf(path.size, index + program.size)) ==
                            program.subList(0, minOf(program.size, path.size - index))
                        ) {
                            stack += CompressState(
                                main + i,
                                programs,
                                index + program.size
                            )
                        }
                    }
                }
                if (programs.size < 3) {
                    for (end in index + 1 until path.size) {
                        val program = path.subList(index, end)
                        if (program.sumBy { it.length } + program.size - 1 > 20) {
                            break
                        }
                        stack += CompressState(
                            main + programs.size,
                            programs.plusElement(program),
                            index + program.size
                        )
                    }
                }
            }
            return null
        }

        private data class CompressState(
            val main: List<Int>,
            val programs: List<List<String>>,
            val index: Int
        )
    }
}