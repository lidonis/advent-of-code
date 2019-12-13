fun main() {
    val program = InputReader("day7.txt").asLinesOfLongs()[0]
    val max1 = (0..4).toList().permute().map {
        Amplifiers(
            program, it
        ).run()
    }.max()

    println(max1)

    val max2 = (5..9).toList().permute().map {
        Amplifiers(
            program, it
        ).run()
    }.max()

    println(max2)
}


class Amplifiers(private val program: List<Long>, private val phaseSettings: List<Int>) {

    private val amplifiers = phaseSettings.map { IntCodeComputer(program, mutableListOf(it.toLong())) }

    fun run(): Long {
        var signal = 0L
        var currentAmplifier = 0
        var loop = 0
        try {

            while (true) {
                val amplifier = amplifiers[currentAmplifier]
                amplifier.inputs.add(signal);
                signal = runAmplifier(amplifier, loop)
                if (amplifier == amplifiers.last()) {
                    currentAmplifier = 0;
                    loop++
                } else {
                    currentAmplifier++
                }
            }
        } catch (e: IllegalStateException) {
            return signal
        }
    }
}

fun runAmplifier(amplifier: IntCodeComputer, loop: Int): Long {
    var signal: Long? = null
    while (signal == null) {
        amplifier.next()
        signal = amplifier.outputs.getOrNull(loop)
    }
    return signal
}

fun <T> List<T>.permute(): List<List<T>> {
    if (this.size == 1) return listOf(this)
    val perms = mutableListOf<List<T>>()
    val toInsert = this[0]
    for (perm in this.drop(1).permute()) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}