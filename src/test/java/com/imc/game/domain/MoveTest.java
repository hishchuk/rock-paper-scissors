package com.imc.game.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Move Tests")
class MoveTest {

    @Test
    @DisplayName("Should create correct moves from valid input")
    void shouldCreateCorrectMovesFromValidInput() {
        assertEquals(Move.ROCK, Move.fromInput(1));
        assertEquals(Move.PAPER, Move.fromInput(2));
        assertEquals(Move.SCISSORS, Move.fromInput(3));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 5, -1, 10})
    @DisplayName("Should throw exception for invalid input values")
    void shouldThrowExceptionForInvalidInput(int invalidInput) {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> Move.fromInput(invalidInput)
        );
        assertEquals("Please enter 1, 2, or 3.", exception.getMessage());
    }

    @Test
    @DisplayName("Rock should beat Scissors")
    void rockShouldBeatScissors() {
        assertTrue(Move.ROCK.beats(Move.SCISSORS));
        assertFalse(Move.SCISSORS.beats(Move.ROCK));
    }

    @Test
    @DisplayName("Paper should beat Rock")
    void paperShouldBeatRock() {
        assertTrue(Move.PAPER.beats(Move.ROCK));
        assertFalse(Move.ROCK.beats(Move.PAPER));
    }

    @Test
    @DisplayName("Scissors should beat Paper")
    void scissorsShouldBeatPaper() {
        assertTrue(Move.SCISSORS.beats(Move.PAPER));
        assertFalse(Move.PAPER.beats(Move.SCISSORS));
    }

    @Test
    @DisplayName("Same moves should not beat each other")
    void sameMovesDoNotBeatEachOther() {
        assertFalse(Move.ROCK.beats(Move.ROCK));
        assertFalse(Move.PAPER.beats(Move.PAPER));
        assertFalse(Move.SCISSORS.beats(Move.SCISSORS));
    }

    @Test
    @DisplayName("Should return WIN when this move beats other")
    void shouldReturnWinWhenThisBeatsOther() {
        assertEquals(BattleResult.WIN, Move.ROCK.against(Move.SCISSORS));
        assertEquals(BattleResult.WIN, Move.PAPER.against(Move.ROCK));
        assertEquals(BattleResult.WIN, Move.SCISSORS.against(Move.PAPER));
    }

    @Test
    @DisplayName("Should return LOSE when other move beats this")
    void shouldReturnLoseWhenOtherBeatsThis() {
        assertEquals(BattleResult.LOSE, Move.SCISSORS.against(Move.ROCK));
        assertEquals(BattleResult.LOSE, Move.ROCK.against(Move.PAPER));
        assertEquals(BattleResult.LOSE, Move.PAPER.against(Move.SCISSORS));
    }

    @Test
    @DisplayName("Should return DRAW when moves are the same")
    void shouldReturnDrawWhenMovesAreTheSame() {
        assertEquals(BattleResult.DRAW, Move.ROCK.against(Move.ROCK));
        assertEquals(BattleResult.DRAW, Move.PAPER.against(Move.PAPER));
        assertEquals(BattleResult.DRAW, Move.SCISSORS.against(Move.SCISSORS));
    }

    @Test
    @DisplayName("Should return correct input values")
    void shouldReturnCorrectInputValues() {
        assertEquals(1, Move.ROCK.getInputValue());
        assertEquals(2, Move.PAPER.getInputValue());
        assertEquals(3, Move.SCISSORS.getInputValue());
    }

    @Test
    @DisplayName("Should return correct display names")
    void shouldReturnCorrectDisplayNames() {
        assertEquals("Rock", Move.ROCK.getDisplayName());
        assertEquals("Paper", Move.PAPER.getDisplayName());
        assertEquals("Scissors", Move.SCISSORS.getDisplayName());
    }

    @Test
    @DisplayName("Should format input options correctly")
    void shouldFormatInputOptionsCorrectly() {
        assertEquals("1 - Rock", Move.ROCK.getInputOption());
        assertEquals("2 - Paper", Move.PAPER.getInputOption());
        assertEquals("3 - Scissors", Move.SCISSORS.getInputOption());
    }
}
