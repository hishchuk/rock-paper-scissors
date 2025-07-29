package com.imc.game;

import com.imc.game.constants.GameMessages;
import com.imc.game.domain.Player;
import com.imc.game.engine.GameEngine;
import com.imc.game.player.ComputerPlayer;
import com.imc.game.player.HumanPlayer;
import com.imc.game.service.MatchResult;
import com.imc.game.service.MatchService;

import java.util.Scanner;

public class RockPaperScissorsApplication {

    private static final System.Logger logger = System.getLogger(RockPaperScissorsApplication.class.getName());

    public static void main(String[] args) {
        // show only messages without timestamps/class names
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%n");

        logger.log(System.Logger.Level.INFO, GameMessages.APP_STARTING);

        try (Scanner scanner = new Scanner(System.in)) {
            new RockPaperScissorsApplication().run(scanner);
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, GameMessages.APPLICATION_ERROR, e);
            logger.log(System.Logger.Level.ERROR, GameMessages.UNEXPECTED_ERROR, e.getMessage());
        }

        logger.log(System.Logger.Level.INFO, GameMessages.APP_TERMINATED);
    }

    public void run(Scanner scanner) {
        MatchService matchService = new MatchService(new GameEngine());
        boolean playAgain;

        do {
            MatchResult result = playMatch(matchService, scanner);
            displayMatchResult(result);
            playAgain = askPlayAgain(scanner);
        } while (playAgain);

        logger.log(System.Logger.Level.INFO, GameMessages.THANKS_FOR_PLAYING);
    }

    private MatchResult playMatch(MatchService matchService, Scanner scanner) {
        Player humanPlayer = new HumanPlayer(GameMessages.DEFAULT_PLAYER_NAME, scanner, logger);
        Player computerPlayer = new ComputerPlayer(GameMessages.DEFAULT_COMPUTER_NAME);
        return matchService.playMatch(humanPlayer, computerPlayer);
    }

    private void displayMatchResult(MatchResult result) {
        logger.log(System.Logger.Level.INFO, GameMessages.FINAL_RESULT_HEADER);

        if (result.isTied()) {
            logger.log(System.Logger.Level.INFO, GameMessages.MATCH_TIED,
                    result.player1Wins(), result.player2Wins());
        } else {
            int winnerScore = result.isPlayer1Winner() ? result.player1Wins() : result.player2Wins();
            int loserScore = result.isPlayer1Winner() ? result.player2Wins() : result.player1Wins();

            logger.log(System.Logger.Level.INFO, GameMessages.MATCH_WON,
                    result.getWinnerName(), winnerScore, loserScore);
        }
    }

    private boolean askPlayAgain(Scanner scanner) {
        logger.log(System.Logger.Level.INFO, GameMessages.PLAY_AGAIN_PROMPT);
        String choice = scanner.nextLine().trim().toLowerCase();
        return choice.startsWith("y");
    }
}
