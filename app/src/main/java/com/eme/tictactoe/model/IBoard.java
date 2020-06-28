package com.eme.tictactoe.model;

public interface IBoard {
    void newGame();

    boolean nextMove(int row, int col);

    boolean hasWinner();

    Player getWinner();

    Player getCurrentPlayer();

    void switchPlayer();

    boolean isFull();
}
