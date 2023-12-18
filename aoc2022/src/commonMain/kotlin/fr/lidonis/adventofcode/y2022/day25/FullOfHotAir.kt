package fr.lidonis.adventofcode.y2022.day25

class FullOfHotAir(private val lines: List<String>) {

    fun sum() =
        SpecialNumeralAnalogueFuelUnits(
            lines.sumOf { SpecialNumeralAnalogueFuelUnits(it).decimalValue }
        ).stringValue
}
