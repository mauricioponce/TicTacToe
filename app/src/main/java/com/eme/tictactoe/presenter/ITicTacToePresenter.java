package com.eme.tictactoe.presenter;

public interface ITicTacToePresenter {

    /**
     * Set a move for a cell (x, y)
     * View does not care the current player
     * @param row x
     * @param col y
     */
    void setCell(int row, int col);

    /**
     *
     * @return {@code true} if there is a game in progress
     * {@code false} otherwise
     */
    boolean isGameInProgress();

    /**
     * Restart the board for a new game
     */
    void restartGame();
}
