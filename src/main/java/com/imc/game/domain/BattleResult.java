package com.imc.game.domain;

public enum BattleResult {
    WIN("wins"),
    LOSE("loses"),
    DRAW("draws");

    private final String description;

    BattleResult(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
