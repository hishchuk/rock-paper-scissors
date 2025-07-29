package com.imc.game.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GameResult Tests")
class GameResultTest {

    @Test
    @DisplayName("Should create correct GameResult when player1 wins")
    void shouldCreateCorrectGameResultWhenPlayer1Wins() {
        GameResult result = GameResult.of("Alice", Move.ROCK, "Bob", Move.SCISSORS);

        assertEquals("Alice", result.player1Name());
        assertEquals(Move.ROCK, result.player1Move());
        assertEquals("Bob", result.player2Name());
        assertEquals(Move.SCISSORS, result.player2Move());
        assertEquals(BattleResult.WIN, result.player1Result());
        assertEquals("Alice", result.winnerName());
        assertFalse(result.isDraw());
    }

    @Test
    @DisplayName("Should create correct GameResult when player2 wins")
    void shouldCreateCorrectGameResultWhenPlayer2Wins() {
        GameResult result = GameResult.of("Alice", Move.ROCK, "Bob", Move.PAPER);

        assertEquals("Alice", result.player1Name());
        assertEquals(Move.ROCK, result.player1Move());
        assertEquals("Bob", result.player2Name());
        assertEquals(Move.PAPER, result.player2Move());
        assertEquals(BattleResult.LOSE, result.player1Result());
        assertEquals("Bob", result.winnerName());
        assertFalse(result.isDraw());
    }

    @Test
    @DisplayName("Should create correct GameResult when draw")
    void shouldCreateCorrectGameResultWhenDraw() {
        GameResult result = GameResult.of("Alice", Move.ROCK, "Bob", Move.ROCK);

        assertEquals("Alice", result.player1Name());
        assertEquals(Move.ROCK, result.player1Move());
        assertEquals("Bob", result.player2Name());
        assertEquals(Move.ROCK, result.player2Move());
        assertEquals(BattleResult.DRAW, result.player1Result());
        assertNull(result.winnerName());
        assertTrue(result.isDraw());
    }

    @Test
    @DisplayName("Should handle all possible move combinations correctly")
    void shouldHandleAllMoveCombinationsCorrectly() {
        // Test all winning combinations for player1
        GameResult rockVsScissors = GameResult.of("P1", Move.ROCK, "P2", Move.SCISSORS);
        assertEquals("P1", rockVsScissors.winnerName());

        GameResult paperVsRock = GameResult.of("P1", Move.PAPER, "P2", Move.ROCK);
        assertEquals("P1", paperVsRock.winnerName());

        GameResult scissorsVsPaper = GameResult.of("P1", Move.SCISSORS, "P2", Move.PAPER);
        assertEquals("P1", scissorsVsPaper.winnerName());

        // Test all winning combinations for player2
        GameResult scissorsVsRock = GameResult.of("P1", Move.SCISSORS, "P2", Move.ROCK);
        assertEquals("P2", scissorsVsRock.winnerName());

        GameResult rockVsPaper = GameResult.of("P1", Move.ROCK, "P2", Move.PAPER);
        assertEquals("P2", rockVsPaper.winnerName());

        GameResult paperVsScissors = GameResult.of("P1", Move.PAPER, "P2", Move.SCISSORS);
        assertEquals("P2", paperVsScissors.winnerName());

        // Test all draw combinations
        GameResult rockVsRock = GameResult.of("P1", Move.ROCK, "P2", Move.ROCK);
        assertTrue(rockVsRock.isDraw());

        GameResult paperVsPaper = GameResult.of("P1", Move.PAPER, "P2", Move.PAPER);
        assertTrue(paperVsPaper.isDraw());

        GameResult scissorsVsScissors = GameResult.of("P1", Move.SCISSORS, "P2", Move.SCISSORS);
        assertTrue(scissorsVsScissors.isDraw());
    }
}
