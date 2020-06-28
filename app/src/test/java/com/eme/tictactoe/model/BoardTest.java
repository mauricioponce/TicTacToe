package com.eme.tictactoe.model;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertFalse;

public class BoardTest {

    private Board board;
    private Cell cell_player_1;
    private Cell cell_player_2;

    @Before
    public void setUp() {
        Player player1 = new Player("Martha", 1);
        Player player2 = new Player("Jonas", 2);

        cell_player_1 = new Cell(player1);
        cell_player_2 = new Cell(player2);

        board = new Board(player1, player2);
    }

    @Test
    public void nextMove_ok() {
        // Given
        int row = 0;
        int col = 0;

        // When
        boolean result = board.nextMove(row, col);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void nextMove_cellAssigned() {
        // Given
        int row = 0;
        int col = 0;
        board.nextMove(row, col);

        // When
        boolean result = board.nextMove(row, col);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void isEmptyNullOrEmpty_null() {
        // Given

        // When
        boolean cellNullOrEmpty = board.isCellNullOrEmpty(null);

        // Then
        assertThat(cellNullOrEmpty).isTrue();
    }

    @Test
    public void isCellNullOrEmpty_notNull() {
        // Given

        // When
        boolean result = board.isCellNullOrEmpty(cell_player_1);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void switchPlayers_1to2() {
        // Given
        Player currentPlayer = board.currentPlayer;

        // When
        board.switchPlayer();

        // Then
        Player switchedPlayer = board.currentPlayer;
        assertThat(currentPlayer).isNotEqualTo(switchedPlayer);
    }

    @Test
    public void switchPlayers_2() {
        // Given
        Player currentPlayer = board.currentPlayer;

        // When
        board.switchPlayer();
        board.switchPlayer();

        // Then
        Player switchedPlayer = board.currentPlayer;
        assertThat(currentPlayer).isEqualTo(switchedPlayer);
    }

    @Test
    public void hasThreeHorizontal_match() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[0][1] = cell_player_1;
        board.cells[0][2] = cell_player_1;

        // When
        boolean result = board.hasThreeHorizontal();

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void hasThreeHorizontal_differentPlayer() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[0][1] = cell_player_1;
        board.cells[0][2] = cell_player_2;

        // When
        boolean result = board.hasThreeHorizontal();

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void hasThreeHorizontal_notMatch() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[0][1] = cell_player_1;

        // When
        boolean result = board.hasThreeHorizontal();

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void hasThreeVertical_match() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[1][0] = cell_player_1;
        board.cells[2][0] = cell_player_1;

        // When
        boolean result = board.hasThreeVertical();

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void hasThreeVertical_differentPlayer() {
        board.cells[0][0] = cell_player_1;
        board.cells[1][0] = cell_player_1;
        board.cells[2][0] = cell_player_2;

        assertFalse(board.hasThreeVertical());
    }

    @Test
    public void hasThreeVertical_notMatch() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[1][1] = cell_player_1;
        board.cells[0][2] = cell_player_1;

        // When
        boolean result = board.hasThreeVertical();

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void hasThreeDiagonal_matchLeft2Right() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[1][1] = cell_player_1;
        board.cells[2][2] = cell_player_1;

        // when
        boolean result = board.hasThreeDiagonal();

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void hasThreeDiagonal_matchRight2Left() {
        // Given
        board.cells[2][0] = cell_player_1;
        board.cells[1][1] = cell_player_1;
        board.cells[0][2] = cell_player_1;

        // When
        boolean result = board.hasThreeDiagonal();

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void hasThreeDiagonal_differentPlayer() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[1][1] = cell_player_1;
        board.cells[2][2] = cell_player_2;

        // When
        boolean result = board.hasThreeDiagonal();

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void hasThreeDiagonal_notMatch() {
        // Given
        board.cells[0][0] = cell_player_1;
        board.cells[1][1] = cell_player_1;

        // When
        boolean result = board.hasThreeDiagonal();

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void areEqual_ok() {
        // Given

        // When
        boolean result = board.areEqual(cell_player_1, cell_player_1, cell_player_1);

        // Then
        assertThat(result).isTrue();
    }

    @Test
    public void areEqual_not_equal() {
        // Given

        // When
        boolean result = board.areEqual(cell_player_1, cell_player_2);

        // Then
        assertThat(result).isFalse();
    }

    @Test
    public void isFull_ok() {
        // Given - Fill the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board.cells[row][col] = cell_player_1;
            }
        }

        // When
        boolean full = board.isFull();

        // Then
        assertThat(full).isTrue();
    }

    @Test
    public void isFull_notFull() {
        // Given - Empty board

        // When
        boolean full = board.isFull();

        // Then
        assertThat(full).isFalse();
    }

    @Test
    public void isFull_almostFull() {
        // Given - Almost fill the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 2; col++) {
                board.cells[row][col] = cell_player_1;
            }
        }

        // When
        boolean full = board.isFull();

        // Then
        assertThat(full).isFalse();
    }

    @Test
    public void hasWinner_ok() {
        // Given - A winner diagonal
        board.cells[0][0] = cell_player_1;
        board.cells[1][1] = cell_player_1;
        board.cells[2][2] = cell_player_1;

        // When
        boolean winner = board.hasWinner();

        // Then
        assertThat(winner).isTrue();
    }

    @Test
    public void hasWinner_noWinner() {
        // Given - A NO winner diagonal
        board.cells[0][0] = cell_player_1;
        board.cells[1][1] = cell_player_1;

        // When
        boolean winner = board.hasWinner();

        // Then
        assertThat(winner).isFalse();
    }
}