package fr.lidonis.adventofcode.y2020.day20

import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

private val SEA_MONSTER = """
                      # 
    #    ##    ##    ###
     #  #  #  #  #  #   
""".trimIndent().lines()

private const val SEA_MONSTER_HEIGHT = 3
private const val SEA_MONSTER_LENGTH = 20

class SeaMonsterChaser {

    private val seaMonsterMatrix = Matrix(
        MutableList<ArrayList<Boolean>>(SEA_MONSTER_HEIGHT) {
            ArrayList(SEA_MONSTER_LENGTH)
        }.also {
            for ((i, line) in SEA_MONSTER.withIndex()) {
                for ((j, c) in line.withIndex()) {
                    it[i].add(j, c == '#')
                }
            }
        })

    fun findSeaMonsters(image: Set<Position>) = seaMonsterMatrix.allOrientations()
        .map { findSeaMonsters(PositionSet(image), matrixToPositionSet(it)) }
        .flatten().toSet()

    private fun findSeaMonsters(image: PositionSet, monster: PositionSet): Set<Position> {
        return sequence {
            val endX = image.boundingBox.end.x + monster.boundingBox.start.x - monster.boundingBox.end.x
            val endY = image.boundingBox.end.y + monster.boundingBox.start.y - monster.boundingBox.end.y
            for (i in image.boundingBox.start.x..endX) {
                for (j in image.boundingBox.start.y..endY) {
                    val newMonster = monster.moveTo(Position(i, j))
                    if (image.containsAll(newMonster)) yieldAll(newMonster.positions)
                }
            }
        }.toSet()
    }

    private fun matrixToPositionSet(monster: Matrix<Boolean>) = PositionSet(sequence {
        for (x in 0 until monster.row) {
            for (y in 0 until monster.column) {
                if (monster[x][y]) yield(Position(x, y))
            }
        }
    }.toSet())

}
