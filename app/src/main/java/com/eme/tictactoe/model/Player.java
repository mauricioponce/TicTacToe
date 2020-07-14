package com.eme.tictactoe.model;

import com.eme.tictactoe.R;

public class Player {

    public static final int RES_PLAYER_1 = R.drawable.ic_player_1;
    public static final int RES_PLAYER_2 = R.drawable.ic_player_2;

    private String name;

    private int turn;

    private int avatar;

    public Player(String name, int turn) {
        this.name = name;
        this.turn = turn;
        this.avatar = -1;
    }

    public Player(String name, int turn, int avatar) {
        this.name = name;
        this.turn = turn;
        this.avatar = avatar;
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
        if(this.avatar > 0) {
            return this.avatar;
        }
        return turn == 1 ? RES_PLAYER_1 : RES_PLAYER_2;
    }
}
