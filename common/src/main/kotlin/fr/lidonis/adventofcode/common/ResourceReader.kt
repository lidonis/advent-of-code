package fr.lidonis.adventofcode.common

import java.io.BufferedReader

class ResourceReader(private val reader: BufferedReader) {

    fun lines(): List<String> = reader.readLines()
    fun text(): String = reader.readText()

    companion object {
        operator fun invoke(fileName: String) =
            object {}::class.java.getResourceAsStream(fileName)?.bufferedReader()?.let(::ResourceReader)
    }
}
