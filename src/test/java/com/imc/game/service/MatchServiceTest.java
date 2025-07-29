package com.imc.game.service;

import com.imc.game.domain.GameResult;
import com.imc.game.domain.Move;
import com.imc.game.domain.Player;
import com.imc.game.engine.GameEngine;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("MatchService Tests")
class MatchServiceTest {

    @Mock
    private GameEngine mockGameEngine;

    @Mock
    private Player mockPlayer1;

    @Mock
    private Player mockPlayer2;

    private MatchService matchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        matchService = new MatchService(mockGameEngine);

        when(mockPlayer1.getName()).thenReturn("Player1");
        when(mockPlayer2.getName()).thenReturn("Player2");
    }

    @Test
    @DisplayName("Should play exactly 3 rounds")
    void shouldPlayExactly3Rounds() {
        // Mock 3 game results where Player1 wins all
        GameResult mockResult = new GameResult("Player1", Move.ROCK, "Player2", Move.SCISSORS, null, "Player1");
        when(mockGameEngine.playRound(mockPlayer1, mockPlayer2)).thenReturn(mockResult);

        matchService.playMatch(mockPlayer1, mockPlayer2);

        // Verify playRound was called exactly 3 times
        verify(mockGameEngine, times(3)).playRound(mockPlayer1, mockPlayer2);
        verify(mockGameEngine, times(3)).displayResult(any(GameResult.class));
    }

    @Test
    @DisplayName("Should return correct match result when Player1 wins")
    void shouldReturnCorrectMatchResultWhenPlayer1Wins() {
        // Player1 wins 2 out of 3 rounds
        GameResult player1Win = GameResult.of("Player1", Move.ROCK, "Player2", Move.SCISSORS);
        GameResult player2Win = GameResult.of("Player1", Move.ROCK, "Player2", Move.PAPER);
        GameResult player1Win2 = GameResult.of("Player1", Move.PAPER, "Player2", Move.ROCK);

        when(mockGameEngine.playRound(mockPlayer1, mockPlayer2))
            .thenReturn(player1Win, player2Win, player1Win2);

        MatchResult result = matchService.playMatch(mockPlayer1, mockPlayer2);

        assertEquals("Player1", result.player1Name());
        assertEquals("Player2", result.player2Name());
        assertEquals(2, result.player1Wins());
        assertEquals(1, result.player2Wins());
        assertTrue(result.isPlayer1Winner());
    }

    @Test
    @DisplayName("Should handle draws correctly")
    void shouldHandleDrawsCorrectly() {
        // All 3 rounds are draws
        GameResult draw1 = GameResult.of("Player1", Move.ROCK, "Player2", Move.ROCK);
        GameResult draw2 = GameResult.of("Player1", Move.PAPER, "Player2", Move.PAPER);
        GameResult draw3 = GameResult.of("Player1", Move.SCISSORS, "Player2", Move.SCISSORS);

        when(mockGameEngine.playRound(mockPlayer1, mockPlayer2))
            .thenReturn(draw1, draw2, draw3);

        MatchResult result = matchService.playMatch(mockPlayer1, mockPlayer2);

        assertEquals(0, result.player1Wins());
        assertEquals(0, result.player2Wins());
        assertTrue(result.isTied());
    }

    @Test
    @DisplayName("Should handle mixed results with draws")
    void shouldHandleMixedResultsWithDraws() {
        // 1 win for Player1, 1 draw, 1 win for Player2
        GameResult player1Win = GameResult.of("Player1", Move.ROCK, "Player2", Move.SCISSORS);
        GameResult draw = GameResult.of("Player1", Move.PAPER, "Player2", Move.PAPER);
        GameResult player2Win = GameResult.of("Player1", Move.ROCK, "Player2", Move.PAPER);

        when(mockGameEngine.playRound(mockPlayer1, mockPlayer2))
            .thenReturn(player1Win, draw, player2Win);

        MatchResult result = matchService.playMatch(mockPlayer1, mockPlayer2);

        assertEquals(1, result.player1Wins());
        assertEquals(1, result.player2Wins());
        assertTrue(result.isTied());
    }
}
