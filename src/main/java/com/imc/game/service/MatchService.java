package com.imc.game.service;

import com.imc.game.constants.GameMessages;
import com.imc.game.domain.GameResult;
import com.imc.game.domain.Player;
import com.imc.game.engine.GameEngine;

/**
 * Manages a complete match of 3 rounds between two players.
 */
public class MatchService {

    private static final System.Logger logger = System.getLogger(MatchService.class.getName());
    private static final int ROUNDS_PER_MATCH = 3;

    private final GameEngine gameEngine;

    public MatchService(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    public MatchResult playMatch(Player player1, Player player2) {
        int player1Wins = 0;
        int player2Wins = 0;

        for (int round = 1; round <= ROUNDS_PER_MATCH; round++) {
            logger.log(System.Logger.Level.INFO, GameMessages.ROUND_HEADER, round);

            GameResult result = gameEngine.playRound(player1, player2);
            gameEngine.displayResult(result);

            if (!result.isDraw()) {
                if (result.winnerName().equals(player1.getName())) {
                    player1Wins++;
                } else {
                    player2Wins++;
                }
            }

            logger.log(System.Logger.Level.INFO, GameMessages.SCORE_FORMAT,
                      player1.getName(), player1Wins, player2.getName(), player2Wins);
        }

        return new MatchResult(player1.getName(), player1Wins, player2.getName(), player2Wins);
    }
}
