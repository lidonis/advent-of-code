package fr.lidonis.adventofcode.y2022.day19

import fr.lidonis.adventofcode.common.get0
import fr.lidonis.adventofcode.common.merge
import fr.lidonis.adventofcode.y2022.day19.Resource.CLAY
import fr.lidonis.adventofcode.y2022.day19.Resource.GEODE
import fr.lidonis.adventofcode.y2022.day19.Resource.OBSIDIAN
import fr.lidonis.adventofcode.y2022.day19.Resource.ORE


private const val BLUEPRINT_REGEX =
    """Blueprint (\d+): Each ore robot costs (\d+) ore. Each clay robot costs (\d+) ore. Each obsidian robot costs (\d+) ore and (\d+) clay. Each geode robot costs (\d+) ore and (\d+) obsidian."""

class NotEnoughMinerals(lines: List<String>) {

    private val blueprints = lines
        .mapNotNull { BLUEPRINT_REGEX.toRegex().matchEntire(it)?.groupValues }
        .map {
            it
                .drop(1) // drop the full line
                .map(String::toInt)
        }
        .map {
            Blueprint(
                id = it[0],
                robotFactory = RobotFactory(
                    listOf(
                        Robot(ORE, mapOf(ORE to it[1])),
                        Robot(CLAY, mapOf(ORE to it[2])),
                        Robot(OBSIDIAN, mapOf(ORE to it[3], CLAY to it[4])),
                        Robot(GEODE, mapOf(ORE to it[5], OBSIDIAN to it[6])),
                    )
                )
            )
        }

    fun sumOfQualityLevels() = blueprints.sumOf { blueprint ->
        blueprint.qualityLevel(24)
    }

    fun timesOfMaxGeodes() = blueprints.take(3).map { blueprint ->
        blueprint.maxGeodes(32)
    }.reduce(Int::times)

}

private data class Blueprint(val id: Int, val robotFactory: RobotFactory) {
    fun qualityLevel(minutes: Int) = maxGeodes(minutes) * id

    fun maxGeodes(minutes: Int): Int {
        var states = setOf(
            State(
                0,
                emptyMap(),
                mapOf(robotFactory.robots.find { it.produce == ORE }!! to 1),
                emptySet()
            )
        )
        var maxGeode = minutes
        repeat(minutes) {
            states = states.flatMap { it.evolve(robotFactory) }.toSet()
            maxGeode = states.maxOf(State::geodes)
            //println("$it ${states.count()}")
            states = states.filter { it.maxPossibleGeodes(minutes, robotFactory) >= maxGeode }.toSet()
            //println("$it ${states.count()}")
        }

        val oreRobot = robotFactory.robotsByResource[ORE]!!
        val obsidianRobot = robotFactory.robotsByResource[OBSIDIAN]!!

        val foundStates = states.filter { it.geodes == maxGeode }.filter { state ->
            state.robots[oreRobot]!! > robotFactory.maxCosts[ORE]!! &&
                    state.robots[obsidianRobot]!! > robotFactory.maxCosts[OBSIDIAN]!!
        }
        foundStates.forEach(::println)

        return maxGeode
    }
}

private data class RobotFactory(val robots: List<Robot>) {

    val maxCosts = robots
        .flatMap { it.costs.entries }
        .groupBy { it.key }
        .mapValues { it.value.maxOf(Map.Entry<Resource, Int>::value) }

    val robotsByResource = robots.associateBy { it.produce }

    fun possibleRobots(resources: Map<Resource, Int>, currentRobots: Map<Robot, Int>): List<Robot> {
        return robots
            .filter { it.canBeProduced(resources) }
            .filter { shouldBeProduced(it, currentRobots.get0(it)) }
    }

    private fun shouldBeProduced(robot: Robot, nbRobot: Int) =
        maxCosts.get0(robot.produce) > nbRobot

}

private data class Robot(val produce: Resource, val costs: Map<Resource, Int>) {
    fun canBeProduced(
        resources: Map<Resource, Int>
    ) = costs.all { it.value <= (resources[it.key] ?: 0) }
}

private enum class Resource {
    ORE, CLAY, OBSIDIAN, GEODE
}

private data class State(
    val minute: Int,
    val resources: Map<Resource, Int>,
    val robots: Map<Robot, Int>,
    val ignoredRobots: Set<Robot>,
) {

    val geodes = resources.get0(GEODE)

    fun evolve(robotFactory: RobotFactory): List<State> {
        val newResources = produceResources()
        val producedResources = resources.merge(newResources, Int::plus)

        val possibleRobots = robotFactory.possibleRobots(resources, robots)

        val buildRobots = possibleRobots - ignoredRobots
        return listOf(State(minute + 1, producedResources, robots, ignoredRobots + possibleRobots)) +
                buildRobots.map {
                    it.produceRobotState(
                        producedResources
                    )
                }
    }

    private fun Robot.produceRobotState(
        producedResources: Map<Resource, Int>
    ): State {
        val newRobotTotal = robots.toMutableMap()
        newRobotTotal[this] = newRobotTotal.get0(this) + 1

        return State(minute + 1, producedResources.merge(costs, Int::minus), newRobotTotal, emptySet())
    }

    private fun produceResources(): Map<Resource, Int> {
        return robots.entries.associate {
            it.key.produce to it.value
        }.toMutableMap()
    }

    fun maxPossibleGeodes(maxMinutes: Int, robotFactory: RobotFactory) =
        geodes +
                (robotFactory.robotsByResource[GEODE]?.let { robots[it] } ?: 0) * (maxMinutes - minute)
}
