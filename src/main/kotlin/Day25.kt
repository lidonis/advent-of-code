fun main() {
    val program = InputReader("day25.txt").asLineOfLongs()
    val computer = IntCodeComputer(program)
    while (true) {
        computer.tryNextOutput(10)?.run {
            val c = toChar()
            print(c)
            if (c == '?') {
                readLine()?.run {
                    computer.input(this)
                }
            }
        }
    }
}