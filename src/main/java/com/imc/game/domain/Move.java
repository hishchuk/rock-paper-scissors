package com.imc.game.domain;

import com.imc.game.constants.GameMessages;

public enum Move {
    ROCK(1, "Rock"),
    PAPER(2, "Paper"),
    SCISSORS(3, "Scissors");

    private final int inputValue;
    private final String displayName;

    Move(int inputValue, String displayName) {
        this.inputValue = inputValue;
        this.displayName = displayName;
    }

    /**
     * Creates a Move from user input (1, 2, or 3).
     *
     * @param input the numeric input from user
     * @return the corresponding Move
     * @throws IllegalArgumentException if input is not 1, 2, or 3
     */
    public static Move fromInput(int input) {
        return switch (input) {
            case 1 -> ROCK;
            case 2 -> PAPER;
            case 3 -> SCISSORS;
            default -> throw new IllegalArgumentException(GameMessages.INVALID_INPUT_RANGE);
        };
    }

    /**
     * Determines if this move beats the other move.
     * Uses pattern matching for clear game logic.
     */
    public boolean beats(Move other) {
        return switch (this) {
            case ROCK -> other == SCISSORS;     // Rock beats (blunts) scissors
            case PAPER -> other == ROCK;        // Paper beats (wraps) rock
            case SCISSORS -> other == PAPER;    // Scissors beats (cuts) paper
        };
    }

    /**
     * Compares this move against another and returns the battle result.
     */
    public BattleResult against(Move other) {
        if (this.beats(other)) {
            return BattleResult.WIN;
        } else if (other.beats(this)) {
            return BattleResult.LOSE;
        } else {
            return BattleResult.DRAW;
        }
    }

    public int getInputValue() {
        return inputValue;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns a formatted string showing the input option.
     */
    public String getInputOption() {
        return GameMessages.MOVE_OPTION_FORMAT.formatted(inputValue, displayName);
    }
}
