package fr.lidonis.adventofcode.y2019.day1

import InputReader

fun main() {
    println("The sum of the fuel requirements for all of the modules on the spacecraft is ${part1()}")
    println("the sum of the fuel requirements for all of the modules on your spacecraft when also taking into account the mass of the added fuel is ${part2()}")
}

fun part1(): Int {
    val input = InputReader("day1.txt").lines.map { it.toInt() }
    return Spacecraft(input).fuelRequirements()
}

fun part2(): Int {
    val input = InputReader("day1.txt").lines.map { it.toInt() }
    return Spacecraft(input).fullFuelRequirements()
}

class Spacecraft(private val moduleMasses: List<Int>) {

    fun fuelRequirements() = moduleMasses.map(::moduleFuel).sum()
    fun fullFuelRequirements() = moduleMasses.map(::recursiveFuel).sum()

    private fun moduleFuel(mass: Int) = mass / 3 - 2
    private fun recursiveFuel(moduleMass: Int) = fuelFuel(moduleFuel(moduleMass))
    private fun fuelFuel(fuelMass: Int): Int = if (fuelMass < 0) 0 else fuelMass + fuelFuel(moduleFuel(fuelMass))
}