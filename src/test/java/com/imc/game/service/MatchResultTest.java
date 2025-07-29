package com.imc.game.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("MatchResult Tests")
class MatchResultTest {

    @Test
    @DisplayName("Should correctly identify player1 as winner")
    void shouldCorrectlyIdentifyPlayer1AsWinner() {
        MatchResult result = new MatchResult("Alice", 2, "Bob", 1);

        assertTrue(result.isPlayer1Winner());
        assertFalse(result.isPlayer2Winner());
        assertFalse(result.isTied());
        assertEquals("Alice", result.getWinnerName());
    }

    @Test
    @DisplayName("Should correctly identify player2 as winner")
    void shouldCorrectlyIdentifyPlayer2AsWinner() {
        MatchResult result = new MatchResult("Alice", 1, "Bob", 2);

        assertFalse(result.isPlayer1Winner());
        assertTrue(result.isPlayer2Winner());
        assertFalse(result.isTied());
        assertEquals("Bob", result.getWinnerName());
    }

    @Test
    @DisplayName("Should correctly identify tied match")
    void shouldCorrectlyIdentifyTiedMatch() {
        MatchResult result = new MatchResult("Alice", 1, "Bob", 1);

        assertFalse(result.isPlayer1Winner());
        assertFalse(result.isPlayer2Winner());
        assertTrue(result.isTied());
        assertNull(result.getWinnerName());
    }

    @Test
    @DisplayName("Should handle perfect game scenario")
    void shouldHandlePerfectGameScenario() {
        MatchResult result = new MatchResult("Player", 3, "Computer", 0);

        assertTrue(result.isPlayer1Winner());
        assertEquals("Player", result.getWinnerName());
        assertEquals(3, result.player1Wins());
        assertEquals(0, result.player2Wins());
    }

    @Test
    @DisplayName("Should handle zero-zero tie")
    void shouldHandleZeroZeroTie() {
        MatchResult result = new MatchResult("Player", 0, "Computer", 0);

        assertTrue(result.isTied());
        assertNull(result.getWinnerName());
    }
}
