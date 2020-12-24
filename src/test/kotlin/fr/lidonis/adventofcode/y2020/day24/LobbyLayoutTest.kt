package fr.lidonis.adventofcode.y2020.day24

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class LobbyLayoutTest {

    val largeExample = """
        sesenwnenenewseeswwswswwnenewsewsw
        neeenesenwnwwswnenewnwwsewnenwseswesw
        seswneswswsenwwnwse
        nwnwneseeswswnenewneswwnewseswneseene
        swweswneswnenwsewnwneneseenw
        eesenwseswswnenwswnwnwsewwnwsene
        sewnenenenesenwsewnenwwwse
        wenwwweseeeweswwwnwwe
        wsweesenenewnwwnwsenewsenwwsesesenwne
        neeswseenwwswnwswswnw
        nenwswwsewswnenenewsenwsenwnesesenew
        enewnwewneswsewnwswenweswnenwsenwsw
        sweneswneswneneenwnewenewwneswswnese
        swwesenesewenwneswnwwneseswwne
        enesenwswwswneneswsenwnewswseenwsese
        wnwnesenesenenwwnenwsewesewsesesew
        nenewswnwewswnenesenwnesewesw
        eneswnwswnwsenenwnwnwwseeswneewsenese
        neswnwewnwnwseenwseesewsenwsweewe
        wseweeenwnesenwwwswnew
    """.trimIndent()

    @Test
    fun countBlackTiles() {
        assertThat(LobbyLayout.fromLines(largeExample.lines()).countBlackTiles()).isEqualTo(10)
    }

    @Test
    fun `evolve 1`() {
        assertThat(LobbyLayout.fromLines(largeExample.lines()).evolve(1).countBlackTiles()).isEqualTo(15)
    }
}