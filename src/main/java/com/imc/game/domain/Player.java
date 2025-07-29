package com.imc.game.domain;

public interface Player {

    /**
     * Gets the player's name for display purposes.
     */
    String getName();

    /**
     * Gets the player's move choice.
     * Implementation depends on player type (human or computer).
     */
    Move getMove();
}
