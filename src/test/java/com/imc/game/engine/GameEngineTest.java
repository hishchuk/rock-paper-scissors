package com.imc.game.engine;

import com.imc.game.domain.GameResult;
import com.imc.game.domain.Move;
import com.imc.game.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("GameEngine Tests")
class GameEngineTest {

    @Mock
    private Player mockPlayer1;

    @Mock
    private Player mockPlayer2;

    private GameEngine gameEngine;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        gameEngine = new GameEngine();

        when(mockPlayer1.getName()).thenReturn("Alice");
        when(mockPlayer2.getName()).thenReturn("Bob");
    }

    @Test
    @DisplayName("Should execute complete round and return correct result")
    void shouldExecuteCompleteRoundAndReturnCorrectResult() {
        when(mockPlayer1.getMove()).thenReturn(Move.ROCK);
        when(mockPlayer2.getMove()).thenReturn(Move.SCISSORS);

        GameResult result = gameEngine.playRound(mockPlayer1, mockPlayer2);

        assertEquals("Alice", result.player1Name());
        assertEquals(Move.ROCK, result.player1Move());
        assertEquals("Bob", result.player2Name());
        assertEquals(Move.SCISSORS, result.player2Move());
        assertEquals("Alice", result.winnerName());
        assertFalse(result.isDraw());

        verify(mockPlayer1).getMove();
        verify(mockPlayer2).getMove();
    }

    @Test
    @DisplayName("Should handle draw scenario")
    void shouldHandleDrawScenario() {
        when(mockPlayer1.getMove()).thenReturn(Move.PAPER);
        when(mockPlayer2.getMove()).thenReturn(Move.PAPER);

        GameResult result = gameEngine.playRound(mockPlayer1, mockPlayer2);

        assertEquals("Alice", result.player1Name());
        assertEquals(Move.PAPER, result.player1Move());
        assertEquals("Bob", result.player2Name());
        assertEquals(Move.PAPER, result.player2Move());
        assertNull(result.winnerName());
        assertTrue(result.isDraw());
    }

    @Test
    @DisplayName("Should handle all winning combinations")
    void shouldHandleAllWinningCombinations() {
        // Test Rock beats Scissors
        when(mockPlayer1.getMove()).thenReturn(Move.ROCK);
        when(mockPlayer2.getMove()).thenReturn(Move.SCISSORS);
        GameResult result1 = gameEngine.playRound(mockPlayer1, mockPlayer2);
        assertEquals("Alice", result1.winnerName());

        // Test Paper beats Rock
        when(mockPlayer1.getMove()).thenReturn(Move.PAPER);
        when(mockPlayer2.getMove()).thenReturn(Move.ROCK);
        GameResult result2 = gameEngine.playRound(mockPlayer1, mockPlayer2);
        assertEquals("Alice", result2.winnerName());

        // Test Scissors beats Paper
        when(mockPlayer1.getMove()).thenReturn(Move.SCISSORS);
        when(mockPlayer2.getMove()).thenReturn(Move.PAPER);
        GameResult result3 = gameEngine.playRound(mockPlayer1, mockPlayer2);
        assertEquals("Alice", result3.winnerName());
    }

    @Test
    @DisplayName("Should call displayResult without throwing exceptions")
    void shouldCallDisplayResultWithoutThrowingExceptions() {
        GameResult result = GameResult.of("Alice", Move.ROCK, "Bob", Move.SCISSORS);

        assertDoesNotThrow(() -> gameEngine.displayResult(result));
    }
}
