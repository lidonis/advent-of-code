package fr.lidonis.adventofcode.y2024.day6
import fr.lidonis.adventofcode.common.geo.plane.Direction
import fr.lidonis.adventofcode.common.geo.plane.Position
import fr.lidonis.adventofcode.common.geo.plane.PositionSet

typealias State = Pair<Position, Direction>

class GuardGallivant(private val lines: List<String>) {

    private companion object {
        const val OBSTACLE_CHAR = '#'
        const val GUARD_CHAR = '^'

        // build map is reversed from example
        val INITIAL_DIRECTION = Direction.DOWN
    }

    val obstaclePositions = buildMap(OBSTACLE_CHAR).toSet()
    val guardStartState = buildMap(GUARD_CHAR).single() to INITIAL_DIRECTION

    fun part1() = traceGuardPath().size

    fun traceGuardPath(): Set<Position> {
        val (visitedStates) = performGuardMovement(PositionSet(obstaclePositions))
        return visitedStates.map { it.first }.toSet()
    }

    fun part2() = traceGuardPath().parallelStream().filter { newObstacle ->
        causesLoop(PositionSet(obstaclePositions + newObstacle))
    }.count()

    private fun performGuardMovement(obstacles: PositionSet): Pair<Set<State>, State> {
        var currentGuardState = guardStartState
        val visitedStates = mutableSetOf<State>()

        while (currentGuardState.first in obstacles.boundingBox && visitedStates.add(currentGuardState)) {
            currentGuardState = nextGuardMove(currentGuardState, obstacles)
        }
        return visitedStates to currentGuardState
    }

    private fun nextGuardMove(guardState: State, obstacles: PositionSet): State {
        val nextPosition = guardState.first + guardState.second
        return if (nextPosition in obstacles) {
            guardState.first to nextDirectionOnObstacle(guardState.second)
        } else {
            nextPosition to guardState.second
        }
    }

    // build map is reversed from example
    private fun nextDirectionOnObstacle(direction: Direction) = direction.turnLeft()

    private fun causesLoop(obstacles: PositionSet): Boolean {
        val (path, guard) = performGuardMovement(obstacles)
        return guard in path
    }

    private fun buildMap(charToFind: Char) = sequence {
        for ((y, line) in lines.withIndex()) {
            for ((x, char) in line.withIndex()) {
                if (char == charToFind) yield(Position(x, y))
            }
        }
    }
}
