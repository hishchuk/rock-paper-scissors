package com.imc.game.player;

import com.imc.game.constants.GameMessages;
import com.imc.game.domain.Move;
import com.imc.game.domain.Player;

import java.util.Random;

public class ComputerPlayer implements Player {

    private static final System.Logger logger = System.getLogger(ComputerPlayer.class.getName());

    private final String name;
    private final Random random;
    private final Move[] moves;

    public ComputerPlayer(String name) {
        this.name = name;
        this.random = new Random();
        this.moves = Move.values();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Move getMove() {
        Move selectedMove = moves[random.nextInt(moves.length)];

        logger.log(System.Logger.Level.INFO, GameMessages.COMPUTER_CHOSE, selectedMove.getDisplayName());

        return selectedMove;
    }
}
