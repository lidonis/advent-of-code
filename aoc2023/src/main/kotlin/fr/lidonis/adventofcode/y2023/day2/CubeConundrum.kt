package fr.lidonis.adventofcode.y2023.day2

import fr.lidonis.adventofcode.common.multiply

private const val MAX_RED_CUBES = 12
private const val MAX_GREEN_CUBES = 13
private const val MAX_BLUE_CUBES = 14

class CubeConundrum(lines: List<String>) {

    private val games = lines.map { line ->
        Game.parse(line)
    }

    fun part1() = games.sumOf { game ->
        if (game.isValid()) game.id else 0
    }

    fun part2() = games.sumOf(Game::power)

    private data class Game(val id: Int, val revealedCubes: List<RevealedCubes>) {

        fun isValid() = revealedCubes.all { show ->
            show.isValid()
        }

        fun power(): Int = revealedCubes
            .flatMap { show -> show.cubes }
            .groupBy { cube -> cube.color }
            .map { (_, cubes) ->
                cubes.maxOf { cube -> cube.count }
            }.multiply()

        private data class RevealedCubes(val cubes: List<Cubes>) {
            fun isValid() = cubes.all { cube ->
                when (cube.color) {
                    Color.RED -> cube.count <= MAX_RED_CUBES
                    Color.GREEN -> cube.count <= MAX_GREEN_CUBES
                    Color.BLUE -> cube.count <= MAX_BLUE_CUBES
                }
            }
        }
        private data class Cubes(val color: Color, val count: Int)
        private enum class Color { BLUE, RED, GREEN;

            companion object {
                fun find(color: String) = entries.first { it.name.lowercase() == color }
            }
        }

        companion object {
            fun parse(line: String): Game {
                val game = line.split(":")
                val id = game[0].trim().split(" ")[1].toInt()
                val revealedCubes = game[1].split(";").map { show ->
                    val cubes = show.split(",").map { cube ->
                        val color = cube.trim().split(" ")[1]
                        val count = cube.trim().split(" ")[0].toInt()
                        Cubes(Color.find(color), count)
                    }
                    RevealedCubes(cubes)
                }
                return Game(id, revealedCubes)
            }
        }
    }
}
