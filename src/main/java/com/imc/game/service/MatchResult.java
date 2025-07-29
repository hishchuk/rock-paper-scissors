package com.imc.game.service;

public record MatchResult(
    String player1Name,
    int player1Wins,
    String player2Name,
    int player2Wins
) {

    public boolean isPlayer1Winner() {
        return player1Wins > player2Wins;
    }

    public boolean isPlayer2Winner() {
        return player2Wins > player1Wins;
    }

    public boolean isTied() {
        return player1Wins == player2Wins;
    }

    public String getWinnerName() {
        if (isPlayer1Winner()) return player1Name;
        if (isPlayer2Winner()) return player2Name;
        return null;
    }
}
