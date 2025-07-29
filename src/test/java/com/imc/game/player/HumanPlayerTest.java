package com.imc.game.player;

import com.imc.game.domain.Move;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.StringReader;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@DisplayName("HumanPlayer Tests")
class HumanPlayerTest {

    @Mock
    private System.Logger mockLogger;

    private HumanPlayer player;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return correct name")
    void shouldReturnCorrectName() {
        Scanner scanner = new Scanner(new StringReader("1\n"));
        player = new HumanPlayer("TestPlayer", scanner, mockLogger);

        assertEquals("TestPlayer", player.getName());
    }

    @Test
    @DisplayName("Should return ROCK when user enters 1")
    void shouldReturnRockWhenUserEnters1() {
        Scanner scanner = new Scanner(new StringReader("1\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        Move move = player.getMove();
        assertEquals(Move.ROCK, move);
    }

    @Test
    @DisplayName("Should return PAPER when user enters 2")
    void shouldReturnPaperWhenUserEnters2() {
        Scanner scanner = new Scanner(new StringReader("2\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        Move move = player.getMove();
        assertEquals(Move.PAPER, move);
    }

    @Test
    @DisplayName("Should return SCISSORS when user enters 3")
    void shouldReturnScissorsWhenUserEnters3() {
        Scanner scanner = new Scanner(new StringReader("3\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        Move move = player.getMove();
        assertEquals(Move.SCISSORS, move);
    }

    @Test
    @DisplayName("Should handle invalid input and retry")
    void shouldHandleInvalidInputAndRetry() {
        // First invalid input (4), then valid input (2)
        Scanner scanner = new Scanner(new StringReader("4\n2\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        Move move = player.getMove();
        assertEquals(Move.PAPER, move);

        // Verify warning was logged for invalid input
        verify(mockLogger, atLeastOnce()).log(eq(System.Logger.Level.WARNING), anyString());
    }

    @Test
    @DisplayName("Should handle non-numeric input and retry")
    void shouldHandleNonNumericInputAndRetry() {
        // First invalid input (abc), then valid input (1)
        Scanner scanner = new Scanner(new StringReader("abc\n1\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        Move move = player.getMove();
        assertEquals(Move.ROCK, move);

        // Verify warning was logged for invalid format
        verify(mockLogger, atLeastOnce()).log(eq(System.Logger.Level.WARNING), anyString());
    }

    @Test
    @DisplayName("Should handle empty input and retry")
    void shouldHandleEmptyInputAndRetry() {
        // First empty input, then valid input (3)
        Scanner scanner = new Scanner(new StringReader("\n3\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        Move move = player.getMove();
        assertEquals(Move.SCISSORS, move);

        // Verify warning was logged for empty input
        verify(mockLogger, atLeastOnce()).log(eq(System.Logger.Level.WARNING), anyString());
    }

    @Test
    @DisplayName("Should handle multiple invalid inputs before valid one")
    void shouldHandleMultipleInvalidInputsBeforeValidOne() {
        // Multiple invalid inputs, then valid input (1)
        Scanner scanner = new Scanner(new StringReader("0\nabc\n\n5\n1\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        Move move = player.getMove();
        assertEquals(Move.ROCK, move);

        // Verify multiple warnings were logged
        verify(mockLogger, atLeast(4)).log(eq(System.Logger.Level.WARNING), anyString());
    }

    @Test
    @DisplayName("Should display move options and prompt correctly")
    void shouldDisplayMoveOptionsAndPromptCorrectly() {
        Scanner scanner = new Scanner(new StringReader("2\n"));
        player = new HumanPlayer("Player", scanner, mockLogger);

        player.getMove();

        // Verify that the move options were displayed
        verify(mockLogger, atLeastOnce()).log(eq(System.Logger.Level.INFO), contains("Choose your move"));
        // Verify that the choice prompt was displayed
        verify(mockLogger, atLeastOnce()).log(eq(System.Logger.Level.INFO), eq("Enter your choice: "));
    }
}
