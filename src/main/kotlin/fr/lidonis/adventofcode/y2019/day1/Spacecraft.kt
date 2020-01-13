package fr.lidonis.adventofcode.y2019.day1

class Spacecraft(private val moduleMasses: List<Int>) {

    fun fuelRequirements() = moduleMasses.map(::fuel).sum()
    fun fullFuelRequirements() = moduleMasses.map(::fullFuel).sum()

    private fun fuel(mass: Int) = mass / 3 - 2
    private fun fullFuel(moduleMass: Int) = fuelFuel(fuel(moduleMass))
    private fun fuelFuel(fuelMass: Int): Int = if (fuelMass < 0) 0 else fuelMass + fuelFuel(fuel(fuelMass))
}
