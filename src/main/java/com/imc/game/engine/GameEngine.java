package com.imc.game.engine;

import com.imc.game.constants.GameMessages;
import com.imc.game.domain.GameResult;
import com.imc.game.domain.Move;
import com.imc.game.domain.Player;

public class GameEngine {

    private static final System.Logger logger = System.getLogger(GameEngine.class.getName());

    public GameResult playRound(Player player1, Player player2) {
        logger.log(System.Logger.Level.INFO, GameMessages.STARTING_ROUND,
                  player1.getName(), player2.getName());

        Move player1Move = player1.getMove();
        Move player2Move = player2.getMove();

        GameResult result = GameResult.of(
            player1.getName(), player1Move,
            player2.getName(), player2Move
        );

        return result;
    }

    public void displayResult(GameResult result) {
        logger.log(System.Logger.Level.INFO, GameMessages.MOVES_FORMAT,
            result.player1Name(), result.player1Move().getDisplayName(),
            result.player2Name(), result.player2Move().getDisplayName());

        if (result.isDraw()) {
            logger.log(System.Logger.Level.INFO, GameMessages.DRAW);
        } else {
            logger.log(System.Logger.Level.INFO, GameMessages.PLAYER_WINS, result.winnerName());
        }
    }
}
