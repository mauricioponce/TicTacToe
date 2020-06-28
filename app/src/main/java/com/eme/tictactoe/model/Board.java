package com.eme.tictactoe.model;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import timber.log.Timber;

public class Board implements IBoard {

    private static final int BOARD_SIZE = 3;

    @VisibleForTesting
    protected Cell[][] cells;

    @VisibleForTesting
    protected Player currentPlayer;

    private Player player1;
    private Player player2;

    public Board(@NonNull Player player1, @NonNull Player player2) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }


    /**
     * Start a new game
     */
    public void newGame() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        this.currentPlayer = player1;
    }

    /**
     * Set a cell if the location is available
     *
     * @return {@code true} if cell was assigned. {@code false} otherwise
     */
    public boolean nextMove(final int row, final int col) {
        Timber.d("nextMove() called with: row = [" + row + "], col = [" + col + "]");
        if (!isCellNullOrEmpty(this.cells[row][col])) return false;

        Cell cell = new Cell(this.currentPlayer);
        this.cells[row][col] = cell;

        return true;
    }

    /**
     * @return {@code true} if one player has three cells in a row from horizontal, vertical or diagonal way
     */
    public boolean hasWinner() {
        return hasThreeHorizontal() || hasThreeVertical() || hasThreeDiagonal();
    }

    public Player getWinner() {
        return currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Change the turn between players
     */
    public void switchPlayer() {
        Timber.d("switchPlayer() called");
        this.currentPlayer = this.currentPlayer == player1 ? player2 : player1;
    }

    /**
     * @return {@code true} if all cells were used. {@code false} otherwise
     */
    public boolean isFull() {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (isCellNullOrEmpty(cell)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Look up for three cells for the same player in rows
     *
     * @return {@code true} for coincidence
     */
    @VisibleForTesting()
    protected boolean hasThreeHorizontal() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (areEqual(cells[i][0], cells[i][1], cells[i][2])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Look up for three cells for the same player in columns
     *
     * @return {@code true} for coincidence
     */
    @VisibleForTesting()
    protected boolean hasThreeVertical() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (areEqual(cells[0][i], cells[1][i], cells[2][i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Look up for three cells for the same player in at least one of the diagonals
     *
     * @return {@code true} for coincidence
     */
    @VisibleForTesting()
    protected boolean hasThreeDiagonal() {
        return areEqual(cells[0][0], cells[1][1], cells[2][2]) ||
                areEqual(cells[0][2], cells[1][1], cells[2][0]);
    }

    /**
     * 2 cells are equal if:
     * 1- Both have non null values
     * 2- Both have equal values
     *
     * @param cells: Cells to check if are equal
     * @return {@code false} if cells are empty or not equals to the first cell
     */
    @VisibleForTesting()
    protected boolean areEqual(@NonNull Cell... cells) {
        for (Cell cell : cells) {
            if (isCellNullOrEmpty(cell)) {
                return false;
            }
        }

        Cell comparisonBase = cells[0];
        for (int i = 1; i < BOARD_SIZE; i++) {
            if (!comparisonBase.equals(cells[i])) {
                return false;
            }
        }

        return true;
    }

    /**
     * A cell is empty if cell is null or actually empty
     *
     * @param cell to evaluate
     * @return {@code true} if null or empty
     */
    @VisibleForTesting()
    protected boolean isCellNullOrEmpty(Cell cell) {
        return cell == null || cell.isEmpty();
    }
}
