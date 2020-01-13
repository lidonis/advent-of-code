package fr.lidonis.adventofcode.y2019.day11

import fr.lidonis.adventofcode.common.Direction
import fr.lidonis.adventofcode.common.Position
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color.BLACK
import fr.lidonis.adventofcode.y2019.day11.EmergencyHullPaintingRobot.Color.WHITE
import fr.lidonis.adventofcode.y2019.intcodecomputer.IOCodeComputer

class EmergencyHullPaintingRobot(private val computer: IOCodeComputer, startingPanelColor: Color) {
    private var direction = Direction.UP
    private var position = Position(0, 0)
    val panels = mutableMapOf<Position, Color>().withDefault { BLACK }

    init {
        panels[position] = startingPanelColor
    }

    fun compute() {
        computer.reset()
        while (computer.hasNext()) {
            computer.input(panels.getValue(position).ordinal.toLong())
            val paint = computer.nextOutput()
            val rotate = computer.nextOutput()
            if (paint == null || rotate == null) break
            paint(paint)
            rotate(rotate)
            move()
        }
    }

    val panelsPaintedAtLeastOnce get() = panels.size

    private fun paint(output: Long) {
        panels[position] = when (output) {
            0L -> BLACK
            1L -> WHITE
            else -> throw IllegalArgumentException("Paint not found")
        }
    }

    private fun rotate(output: Long) {
        when (output) {
            0L -> direction--
            1L -> direction++
            else -> throw IllegalArgumentException("Wrong rotation")
        }
    }

    private fun move() {
        position = position.move(direction)
    }

    enum class Color {
        BLACK, WHITE;
    }

}
