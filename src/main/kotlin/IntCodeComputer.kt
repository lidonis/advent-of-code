class IntCodeComputer(private val program: List<Int>, val inputs: MutableList<Int> = mutableListOf(0)) : Iterator<List<Int>> {

    private var memory: MutableList<Int>
    private var instructionPointer = 0
    var outputs = mutableListOf<Int>()
    private var currentInput = 0

    init {
        memory = program.toMutableList();
    }

    private fun computeStep() {
        val nbInstruction: Int
        val code = memory[instructionPointer].toString()
        val opcode = opcode(code)
        val currentPointer = instructionPointer
        when (opcode) {
            1 -> {
                memory[memory[instructionPointer + 3]] = firstParameter(code) + secondParameter(code)
                nbInstruction = 4
            }
            2 -> {
                memory[memory[instructionPointer + 3]] = firstParameter(code) * secondParameter(code)
                nbInstruction = 4
            }
            3 -> {
                memory[memory[instructionPointer + 1]] = inputs[currentInput++]
                nbInstruction = 2
            }
            4 -> {
                val output = read(1, parameterMode(code, 3))
                outputs.add(output)
                nbInstruction = 2
            }
            5 -> {
                if(firstParameter(code) != 0){
                    instructionPointer = secondParameter(code)
                }
                nbInstruction = 3
            }
            6 -> {
                if(firstParameter(code) == 0){
                    instructionPointer = secondParameter(code)
                }
                nbInstruction = 3
            }
            7 -> {
                if(firstParameter(code) < secondParameter(code)){
                    memory[memory[instructionPointer + 3]] = 1
                }else{
                    memory[memory[instructionPointer + 3]] = 0
                }
                nbInstruction = 4
            }
            8 -> {
                if(firstParameter(code) == secondParameter(code)){
                    memory[memory[instructionPointer + 3]] = 1
                }else{
                    memory[memory[instructionPointer + 3]] = 0
                }
                nbInstruction = 4
            }
            99 -> throw IllegalStateException("Program halted")
            else -> throw IllegalArgumentException("Opcode unknown")
        }
        if(instructionPointer == currentPointer && opcode(memory[instructionPointer].toString()) != 99){
            instructionPointer += nbInstruction
        }
    }

    private fun secondParameter(code: String): Int {
        return read(
            2,
            parameterMode(code, 4)
        )
    }

    private fun firstParameter(code: String) = read(1, parameterMode(code, 3))

    private fun opcode(code: String) = code.takeLast(2).toInt()

    private fun parameterMode(code: String, index: Int) = code.getOrElse(code.length - index) { '0' }

    private fun read(position: Int, parameterMode: Char) =
        when (parameterMode) {
            '0' -> memory[memory[instructionPointer + position]]
            '1' -> memory[instructionPointer + position]
            else -> throw IllegalArgumentException("Parameter mode unknown")
        }

    override fun hasNext() = memory[instructionPointer] != 99

    override fun next(): List<Int> {
        computeStep()
        return memory
    }

    fun input(noun: Int, verb: Int) {
        memory[1] = noun
        memory[2] = verb
    }

    fun reset() {
        memory = program.toMutableList();
        instructionPointer = 0
    }

}