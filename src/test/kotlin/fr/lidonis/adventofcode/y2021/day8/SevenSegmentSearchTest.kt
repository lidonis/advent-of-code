package fr.lidonis.adventofcode.y2021.day8

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SevenSegmentSearchTest {

    private val simpleExample = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"

    private val largeExample = """
        be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
        edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
        fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
        fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
        aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
        fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
        dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
        bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
        egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
        gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
    """.trimIndent()

    @Test
    internal fun countUniqueSegmentNumber() {
        val sevenSegmentSearch = SevenSegmentSearch(largeExample.lines())
        val result = sevenSegmentSearch.countUniqueSegmentNumber()
        assertThat(result).isEqualTo(26)
    }

    @Test
    internal fun `sum values simple example`() {
        val sevenSegmentSearch = SevenSegmentSearch(simpleExample.lines())
        val result = sevenSegmentSearch.sumValues()
        assertThat(result).isEqualTo(5353)
    }

    @Test
    internal fun `sum values large example`() {
        val sevenSegmentSearch = SevenSegmentSearch(largeExample.lines())
        val result = sevenSegmentSearch.sumValues()
        assertThat(result).isEqualTo(61229)
    }
}
