package com.imc.game.constants;

/**
 * Central location for all text constants used in the Rock-Paper-Scissors game.
 * Makes the application easier to maintain and potentially internationalize.
 */
public final class GameMessages {

    // Application lifecycle messages
    public static final String APP_STARTING = "Starting Rock-Paper-Scissors application";
    public static final String APP_TERMINATED = "Application terminated";
    public static final String THANKS_FOR_PLAYING = "Thanks for playing!";
    public static final String APPLICATION_ERROR = "Application error";
    public static final String UNEXPECTED_ERROR = "An unexpected error occurred: {0}";

    // Match and round messages
    public static final String ROUND_HEADER = "\n--- Round {0} ---";
    public static final String FINAL_RESULT_HEADER = "\n=== FINAL RESULT ===";
    public static final String SCORE_FORMAT = "Score: {0} {1} - {2} {3}";
    public static final String MATCH_TIED = "Match tied! Final score: {0}-{1}";
    public static final String MATCH_WON = "{0} won the match! Final score: {1}-{2}";

    // User interaction messages
    public static final String PLAY_AGAIN_PROMPT = "Play another match? (y/n): ";
    public static final String CHOOSE_MOVE = "Choose your move:";
    public static final String ENTER_CHOICE = "Enter your choice: ";

    // Input validation messages
    public static final String PLEASE_ENTER_NUMBER = "Please enter a number.";
    public static final String INVALID_INPUT_FORMAT = "Invalid input. Please enter a number (1, 2, or 3).";
    public static final String INVALID_INPUT_RANGE = "Please enter 1, 2, or 3.";

    // Game result messages
    public static final String DRAW = "Draw!";
    public static final String PLAYER_WINS = "{0} wins!";
    public static final String MOVES_FORMAT = "{0} chose {1}, {2} chose {3}";

    // Move option format
    public static final String MOVE_OPTION_FORMAT = "%d - %s";

    // Logging messages
    public static final String GETTING_MOVE_FOR_PLAYER = "Getting move for player: {0}";
    public static final String PLAYER_CHOSE = "Player chose {0}";
    public static final String COMPUTER_CHOSE = "Computer chose {0}";
    public static final String STARTING_ROUND = "Starting round between {0} and {1}";
    public static final String INVALID_NUMBER_FORMAT = "Invalid number format from player";
    public static final String INVALID_MOVE_CHOICE = "Invalid move choice: {0}";

    // Player names
    public static final String DEFAULT_PLAYER_NAME = "Player";
    public static final String DEFAULT_COMPUTER_NAME = "Computer";

    // Private constructor to prevent instantiation
    private GameMessages() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
