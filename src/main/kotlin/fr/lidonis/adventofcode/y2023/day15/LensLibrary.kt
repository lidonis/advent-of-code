package fr.lidonis.adventofcode.y2023.day15

private const val NB_BOX = 256
private const val MULTIPLIER = 17

class LensLibrary(line: String) {

    private val steps = line.split(",")

    fun part1() = steps.sumOf { it.hash() }

    private fun String.hash() = fold(0) { acc, c ->
        ((acc + c.code) * MULTIPLIER) % NB_BOX
    }

    fun part2(): Int {
        val boxes = Array<MutableMap<String, Int>>(NB_BOX) { mutableMapOf() }
        steps.forEach { step ->
            val (label, focalLength) = step.split("=", "-")
            val hash = label.hash()
            val box = boxes[hash]
            if (focalLength.isEmpty()) {
                box.remove(label)
            } else {
                box[label] = focalLength.toInt()
            }
        }
        return boxes.mapIndexed { boxNumber, box ->
            box.entries.mapIndexed { slotNumber, (_, focalLength) ->
                (boxNumber + 1) * (slotNumber + 1) * focalLength
            }.sum()
        }.sum()
    }
}
