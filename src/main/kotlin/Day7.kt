fun main() {
    val program = InputReader("day7.txt").asLineOfLongs()
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

    private val amplifiers = phaseSettings.map {
        val computer = IntCodeComputer(program)
        computer.input(it.toLong())
        computer
    }

    fun run(): Long {
        var signal = 0L
        var currentAmplifier = 0
        try {
            while (true) {
                val amplifier = amplifiers[currentAmplifier]
                amplifier.input(signal);
                signal = amplifier.nextOutput()!!
                if (amplifier == amplifiers.last()) {
                    currentAmplifier = 0;
                } else {
                    currentAmplifier++
                }
            }
        } catch (e: IllegalStateException) {
            return signal
        }
    }
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