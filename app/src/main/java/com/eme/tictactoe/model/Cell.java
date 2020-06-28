package com.eme.tictactoe.model;

import androidx.annotation.NonNull;

public class Cell {

    private Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "player=" + player +
                '}';
    }

    @Override
    public boolean equals(@NonNull Object obj) {
        if (getClass() != obj.getClass()) return false;

        Cell cell = (Cell) obj;
        return this.player.equals(cell.player);
    }
}
