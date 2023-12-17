package fr.lidonis.adventofcode.common.ocr

import fr.lidonis.adventofcode.common.geo.plane.PositionSet
import org.junit.jupiter.api.Test

class OCRTest {

    @Test
    fun name() {
        OCR.detect(PositionSet(emptySet()))
    }
}
