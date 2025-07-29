package com.imc.game.player;

import com.imc.game.constants.GameMessages;
import com.imc.game.domain.Move;
import com.imc.game.domain.Player;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private static final System.Logger logger = System.getLogger(HumanPlayer.class.getName());

    private final String name;
    private final Scanner scanner;
    private final System.Logger outputLogger;

    public HumanPlayer(String name, Scanner scanner, System.Logger outputLogger) {
        this.name = name;
        this.scanner = scanner;
        this.outputLogger = outputLogger;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move getMove() {
        logger.log(System.Logger.Level.DEBUG, GameMessages.GETTING_MOVE_FOR_PLAYER, name);

        while (true) {
            displayMoveOptions();
            outputLogger.log(System.Logger.Level.INFO, GameMessages.ENTER_CHOICE);

            try {
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    outputLogger.log(System.Logger.Level.WARNING, GameMessages.PLEASE_ENTER_NUMBER);
                    continue;
                }

                int choice = Integer.parseInt(input);
                Move move = Move.fromInput(choice);

                logger.log(System.Logger.Level.INFO, GameMessages.PLAYER_CHOSE, move.getDisplayName());
                return move;

            } catch (NumberFormatException e) {
                outputLogger.log(System.Logger.Level.WARNING, GameMessages.INVALID_INPUT_FORMAT);
                logger.log(System.Logger.Level.DEBUG, GameMessages.INVALID_NUMBER_FORMAT);
            } catch (IllegalArgumentException e) {
                outputLogger.log(System.Logger.Level.WARNING, e.getMessage());
                logger.log(System.Logger.Level.DEBUG, GameMessages.INVALID_MOVE_CHOICE, e.getMessage());
            }
        }
    }

    private void displayMoveOptions() {
        StringBuilder options = new StringBuilder(GameMessages.CHOOSE_MOVE + "\n");
        for (Move move : Move.values()) {
            options.append("  ").append(move.getInputOption()).append("\n");
        }
        outputLogger.log(System.Logger.Level.INFO, options.toString().trim());
    }
}
