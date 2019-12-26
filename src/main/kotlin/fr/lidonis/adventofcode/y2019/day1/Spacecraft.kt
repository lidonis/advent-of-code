package fr.lidonis.adventofcode.y2019.day1

class Spacecraft(private val moduleMasses: List<Int>) {

    fun fuelRequirements() = moduleMasses.map(::moduleFuel).sum()
    fun fullFuelRequirements() = moduleMasses.map(::recursiveFuel).sum()

    private fun moduleFuel(mass: Int) = mass / 3 - 2
    private fun recursiveFuel(moduleMass: Int) = fuelFuel(moduleFuel(moduleMass))
    private fun fuelFuel(fuelMass: Int): Int = if (fuelMass < 0) 0 else fuelMass + fuelFuel(moduleFuel(fuelMass))
}