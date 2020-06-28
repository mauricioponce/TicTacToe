package com.eme.tictactoe.model;

public class Player {

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
}
