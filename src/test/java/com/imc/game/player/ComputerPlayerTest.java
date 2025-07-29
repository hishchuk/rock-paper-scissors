package com.imc.game.player;

import com.imc.game.domain.Move;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ComputerPlayer Tests")
class ComputerPlayerTest {

    @Test
    @DisplayName("Should return correct name")
    void shouldReturnCorrectName() {
        ComputerPlayer player = new ComputerPlayer("TestComputer");
        assertEquals("TestComputer", player.getName());
    }

    @Test
    @DisplayName("Should return a valid move")
    void shouldReturnValidMove() {
        ComputerPlayer player = new ComputerPlayer("Computer");
        Move move = player.getMove();

        assertNotNull(move);
        assertTrue(move == Move.ROCK || move == Move.PAPER || move == Move.SCISSORS);
    }

    @Test
    @DisplayName("Should eventually produce all three moves")
    void shouldEventuallyProduceAllThreeMoves() {
        ComputerPlayer player = new ComputerPlayer("Computer");
        boolean hasRock = false, hasPaper = false, hasScissors = false;

        // Try up to 1000 times to get all three moves (extremely high probability)
        for (int i = 0; i < 1000 && !(hasRock && hasPaper && hasScissors); i++) {
            Move move = player.getMove();
            switch (move) {
                case ROCK -> hasRock = true;
                case PAPER -> hasPaper = true;
                case SCISSORS -> hasScissors = true;
            }
        }

        assertTrue(hasRock, "Computer should eventually choose Rock");
        assertTrue(hasPaper, "Computer should eventually choose Paper");
        assertTrue(hasScissors, "Computer should eventually choose Scissors");
    }
}
