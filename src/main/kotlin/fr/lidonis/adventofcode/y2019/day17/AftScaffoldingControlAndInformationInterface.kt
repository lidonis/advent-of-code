package fr.lidonis.adventofcode.y2019.day17

import fr.lidonis.adventofcode.y2019.intcodecomputer.IntCodeComputerFactory

class AftScaffoldingControlAndInformationInterface(program: String) {

    private val computer = IntCodeComputerFactory.buildASCIIComputer(program)

    private var scaffoldMap = computer.run {
        this.run()
        val input = this.outputs.map { it.toChar() }.joinToString("")
        this.reset()
        ScaffoldMap.from(input)
    }

    fun sumOfTheAlignmentParameters() = scaffoldMap.sumOfTheAlignmentParameters()

    fun amountOfDustCollected(): Long? =
        computer.run {
            memory[0] = 2
            val path = scaffoldMap.findPath()
            val movementRules = MovementRules.from(path)
            input(movementRules.mainRoutine)
            input(movementRules.functionA)
            input(movementRules.functionB)
            input(movementRules.functionC)
            input("n")
            run()
            outputs.last
        }
}
