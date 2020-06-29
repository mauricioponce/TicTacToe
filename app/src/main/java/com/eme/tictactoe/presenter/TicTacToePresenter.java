package com.eme.tictactoe.presenter;

import androidx.annotation.NonNull;

import com.eme.tictactoe.R;
import com.eme.tictactoe.model.Board;
import com.eme.tictactoe.model.IBoard;
import com.eme.tictactoe.model.Player;

import timber.log.Timber;

public class TicTacToePresenter implements ITicTacToePresenter {

    /**
     * Callbacks for update UI
     */
    private ITicTacToeView view;

    /**
     * Model
     */
    private IBoard board;

    /**
     * After winner or draw the game is stopped
     */
    private boolean gameInProgress;

    public TicTacToePresenter(@NonNull ITicTacToeView view) {
        this.view = view;
        this.newGame(); // let's start a new game
    }

    /**************************************************
     ** ITicTacToePresenter interface implementation **
     **************************************************/
    public void setCell(final int row, final int col) {
        Timber.d("setCell() called with: row = [" + row + "], col = [" + col + "]");
        if (this.board.nextMove(row, col)) {
            //Update view with the current player image
            view.setCellImage(this.board.getCurrentPlayer().getAvatar());

            if (this.board.hasWinner()) {
                view.showWinner(board.getWinner());
                gameInProgress = false;
                return;
            }

            if (this.board.isFull()) {
                view.showDraw();
                gameInProgress = false;
            }

            //Ready for the next move
            this.board.switchPlayer();

        } else {
            Timber.d("Invalid cellSelectedAt. Cell already used");
            // nothing to do here for now
            // maybe display a message

        }
    }

    public boolean isGameInProgress() {
        return gameInProgress;
    }

    public void restartGame() {
        this.newGame();
        view.clearScreen();
    }

    /**************************************************
     ******* TicTacToePresenter private methods *******
     **************************************************/
    private void newGame() {
        Player player1 = new Player("Player1", 1);
        Player player2 = new Player("Player2", 2);

        this.board = new Board(player1, player2);

        this.gameInProgress = true;
    }
}
