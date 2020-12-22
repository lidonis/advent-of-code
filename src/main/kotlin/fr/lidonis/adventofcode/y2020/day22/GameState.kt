package fr.lidonis.adventofcode.y2020.day22

data class GameState(val deck1: Deck, val deck2: Deck) {

    fun play(): GameState {
        val (card1, newDeck1) = deck1.draw()
        val (card2, newDeck2) = deck2.draw()
        val player1win = card1 > card2
        return newGameState(player1win, newDeck1, newDeck2, card1, card2)
    }

    fun player1Win() = GameState(deck1, Deck(emptyList()))

    val player1winning = deck2.isEmpty()
    val player2winning = deck1.isEmpty()
    val isFinished get() = player1winning || player2winning
    val winnerScore: Int? = when {
        player1winning -> deck1.score
        player2winning -> deck2.score
        else -> null
    }

    companion object {
        fun newGameState(player1win: Boolean, newDeck1: Deck, newDeck2: Deck, card1: Int, card2: Int) =
            if (player1win) {
                GameState(newDeck1 + card1 + card2, newDeck2)
            } else {
                GameState(newDeck1, newDeck2 + card2 + card1)
            }
    }
}
