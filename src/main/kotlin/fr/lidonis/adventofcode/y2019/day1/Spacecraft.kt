package fr.lidonis.adventofcode.y2019.day1

private const val DIVISOR = 3
private const val SUBTRACTOR = 2

class Spacecraft(private val moduleMasses: List<Int>) {

    fun fuelRequirements() = moduleMasses.map(::fuel).sum()
    fun fullFuelRequirements() = moduleMasses.map(::fullFuel).sum()

    private fun fuel(mass: Int) = mass / DIVISOR - SUBTRACTOR

    private fun fullFuel(moduleMass: Int) = fuelFuel(fuel(moduleMass))
    private fun fuelFuel(fuelMass: Int): Int = if (fuelMass < 0) 0 else fuelMass + fuelFuel(fuel(fuelMass))
}
