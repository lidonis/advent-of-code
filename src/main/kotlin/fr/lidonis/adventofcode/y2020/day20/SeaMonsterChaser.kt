package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import fr.lidonis.adventofcode.common.math.Matrix

private val SEA_MONSTER = """
                                  #
            #    ##    ##    ###   
             #  #  #  #  #  #      
""".trimIndent().lines()

class SeaMonsterChaser {


    fun findSeaMonster(positions: Set<Position>): Set<Position> {
        val positionSet = PositionSet(positions)
        val seaMonsterMatrix =
            Matrix(MutableList<ArrayList<Boolean>>(SEA_MONSTER.size) { ArrayList(SEA_MONSTER.first().length) }.also {
                for ((i, line) in SEA_MONSTER.withIndex()) {
                    for ((j, c) in line.withIndex()) {
                        it[i].add(j, c == '#')
                    }
                }
            })
        return seaMonsterMatrix.allOrientations().map { findSeaMonsters(positionSet, it) }.flatten().toSet()
    }

    private fun findSeaMonsters(image: PositionSet, monster: Matrix<Boolean>): Set<Position> {
        val seaMonsterPosition = mutableSetOf<Position>()

        val monsterPositions = PositionSet(sequence {
            for (x in 0 until monster.row) {
                for (y in 0 until monster.column) {
                    if (monster[x][y]) yield(Position(x, y))
                }
            }
        }.toSet())

        for (i in image.boundingBox.start.x..image.boundingBox.end.x) {
            for (j in image.boundingBox.start.y..image.boundingBox.end.y) {
                val newMonster = monsterPositions.moveTo(Position(i, j))
                println("$i $j")
                newMonster.display()
                if (image.containsAll(newMonster)) seaMonsterPosition += newMonster.positions
            }
        }

        return seaMonsterPosition
    }

}