package fr.lidonis.adventofcode.y2020.day22

import fr.lidonis.adventofcode.common.tail

class CrabCombat(text: String) {

    private val initialState: GameState = text
        .split("\n\n")
        .map { Deck(it.lines().tail.joinToString()) }
        .let { (deck1, deck2) ->
            GameState(deck1, deck2)
        }

    fun play(): Int {
        var currentGameState = initialState
        while (!currentGameState.isFinished) {
            currentGameState = currentGameState.play()
        }
        return currentGameState.winnerScore ?: error("No winner")
    }

    fun playRecursive() = playRecursive(initialState).winnerScore ?: error("No winner")

    private fun playRecursive(initialState: GameState): GameState {
        var currentGameState = initialState
        val gameHistory = mutableSetOf<GameState>()
        while (!currentGameState.isFinished) {
            currentGameState = if (gameHistory.contains(currentGameState)) {
                currentGameState.player1Win()
            } else {
                gameHistory.add(currentGameState)
                val (card1, newDeck1) = currentGameState.deck1.draw()
                val (card2, newDeck2) = currentGameState.deck2.draw()
                if (canPlayRecursive(newDeck1, card1, newDeck2, card2)) {
                    val recursiveState = playRecursive(GameState(newDeck1.copy(card1), newDeck2.copy(card2)))
                    GameState.newGameState(recursiveState.player1winning, newDeck1, newDeck2, card1, card2)
                } else {
                    currentGameState.play()
                }
            }
        }
        return currentGameState
    }

    private fun canPlayRecursive(newDeck1: Deck, card1: Int, newDeck2: Deck, card2: Int) =
        newDeck1.size >= card1 && newDeck2.size >= card2
}
