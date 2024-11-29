package fr.lidonis.adventofcode.y2023.day20

import fr.lidonis.adventofcode.common.math.lcm

private const val PART1_BUTTON_PRESS_COUNT = 1000
private const val PART2_MODULE_NAME = "rx"

class PulsePropagation(private val lines: List<String>) {

    private fun initModules(): Map<String, Module> {
        val modules = lines
            .map { line -> Module.fromString(line) }
            .associateBy { it.name }

        modules
            .filter { it.value is Module.Conjunction }
            .forEach { (_, module) ->
                module as Module.Conjunction
                val inputs = modules.findInputs(module.name)
                module.inputs.putAll(inputs.map { it.name to false })
            }

        return modules
    }

    private fun Map<String, Module>.findInputs(name: String) =
        this.filter { it.value.destinations.contains(name) }.map { it.value }

    fun part1(): Long {
        val modules = initModules()

        val pulses = mutableListOf<Pulse>()
        var lowPulses = 0L
        var highPulses = 0L
        repeat(PART1_BUTTON_PRESS_COUNT) {
            pulses.add(Pulse("button", "broadcaster", false))
            while (pulses.isNotEmpty()) {
                val pulse = pulses.removeFirst()
                if (pulse.high) {
                    highPulses++
                } else {
                    lowPulses++
                }
                val module = modules[pulse.to]
                pulses.addAll(module?.receivePulse(pulse.high, pulse.from) ?: emptyList())
            }
        }

        return lowPulses * highPulses
    }

    fun part2(): Long {
        val modules = initModules()
        val moduleInput = modules.findInputs(PART2_MODULE_NAME).singleOrNull() as? Module.Conjunction
            ?: error("Conjunction module found")
        return countButtonPressToGetHighPulse(moduleInput.inputs.keys)
            .also { println(it) }
            .values.reduce(::lcm)
    }

    private fun countButtonPressToGetHighPulse(moduleNames: Set<String>): Map<String, Long> {
        val modules = initModules()
        val pulses = mutableListOf<Pulse>()
        val modulePulses = mutableMapOf<String, Long>()
        var i = 1L
        while (modulePulses.size < moduleNames.size) {
            pulses.add(Pulse("button", "broadcaster", false))
            while (pulses.isNotEmpty()) {
                val pulse = pulses.removeFirst()
                if (pulse.to in moduleNames && !pulse.high) {
                    modulePulses[pulse.to] = i
                }
                val module = modules[pulse.to]
                pulses.addAll(module?.receivePulse(pulse.high, pulse.from) ?: emptyList())
            }
            i++
        }
        return modulePulses
    }

    sealed interface Module {
        val name: String
        val destinations: List<String>

        fun receivePulse(pulse: Boolean, from: String): List<Pulse>

        class Broadcast(
            override val name: String,
            override val destinations: List<String>,
        ) : Module {

            override fun receivePulse(pulse: Boolean, from: String) =
                destinations.map { Pulse(name, it, pulse) }

            override fun toString(): String {
                return "Broadcast(name='$name', destinations=$destinations)"
            }
        }

        class FlipFlop(
            override val name: String,
            override val destinations: List<String>,
        ) : Module {
            private var on: Boolean = false

            override fun receivePulse(pulse: Boolean, from: String): List<Pulse> {
                return if (pulse) {
                    emptyList()
                } else {
                    on = !on
                    destinations.map { Pulse(name, it, on) }
                }
            }

            override fun toString(): String {
                return "FlipFlop(name='$name', destinations=$destinations, on=$on)"
            }
        }

        class Conjunction(
            override val name: String,
            override val destinations: List<String>,
        ) : Module {
            val inputs: MutableMap<String, Boolean> = mutableMapOf()

            override fun receivePulse(pulse: Boolean, from: String): List<Pulse> {
                inputs[from] = pulse
                return destinations.map { Pulse(name, it, !inputs.all { i -> i.value }) }
            }

            override fun toString(): String {
                return "Conjunction(name='$name', destinations=$destinations, inputs=$inputs)"
            }
        }

        companion object {
            fun fromString(line: String): Module {
                val (name, destinations) = line.split(" -> ")
                return when {
                    name == "broadcaster" -> Broadcast(name, destinations.split(", "))
                    name.startsWith("%") -> FlipFlop(name.drop(1), destinations.split(", "))
                    name.startsWith("&") -> Conjunction(name.drop(1), destinations.split(", "))
                    else -> error("Unknown module type: $name")
                }
            }
        }
    }

    data class Pulse(val from: String, val to: String, val high: Boolean)
}
