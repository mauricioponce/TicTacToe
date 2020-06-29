package com.eme.tictactoe.model;

import com.eme.tictactoe.R;

public class Player {

    public static final int RES_PLAYER_1 = R.drawable.ic_player_1;
    public static final int RES_PLAYER_2 = R.drawable.ic_player_2;

    private String name;

    private int turn;

    public Player(String name, int turn) {
        this.name = name;
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public int getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", turn=" + turn +
                '}';
    }

    public int getAvatar() {
        return turn == 1 ? RES_PLAYER_1 : RES_PLAYER_2;
    }
}
