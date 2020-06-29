package com.eme.tictactoe.presenter;

import com.eme.tictactoe.model.Player;

/**
 * This interface must be implemented by any view (Activity or Fragment)
 * who wants to be notified for changes in its screen for the presenter
 */
public interface ITicTacToeView {

    /**
     * Set the current view with an image resource
     *
     * @param imageResource to shown
     */
    void setCellImage(final int imageResource);

    /**
     * Show the player as winner
     *
     * @param winner
     */
    void showWinner(final Player winner);

    /**
     * Same of showWinner but when it's a draw
     * There is no winner this time
     */
    void showDraw();

    /**
     * Clear all elements in the screen
     */
    void clearScreen();
}
