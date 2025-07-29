package com.imc.game.domain;

public record GameResult(
    String player1Name,
    Move player1Move,
    String player2Name,
    Move player2Move,
    BattleResult player1Result,
    String winnerName
) {

    /**
     * Factory method to create a GameResult from two player moves.
     */
    public static GameResult of(String player1Name, Move player1Move,
                               String player2Name, Move player2Move) {
        BattleResult player1Result = player1Move.against(player2Move);

        String winnerName = switch (player1Result) {
            case WIN -> player1Name;
            case LOSE -> player2Name;
            case DRAW -> null; // No winner in case of draw
        };

        return new GameResult(player1Name, player1Move, player2Name, player2Move,
                            player1Result, winnerName);
    }

    public boolean isDraw() {
        return player1Result == BattleResult.DRAW;
    }
}
