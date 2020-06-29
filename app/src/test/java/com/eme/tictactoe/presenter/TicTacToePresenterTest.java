package com.eme.tictactoe.presenter;

import androidx.core.util.Pair;

import com.eme.tictactoe.model.Player;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;


public class TicTacToePresenterTest {

    /**
     * Our presenter ready to test
     */
    private TicTacToePresenter presenter;

    /**
     * A winner player for showWinner() method
     */
    private Player winner;

    /**
     * List of moves coordinates
     * After do each move, player 1 will be the winner
     */
    private static List<Pair> MOVES_P1_WINS = new ArrayList<>();

    /**
     * List of moves coordinates
     * After do each move, the result will be a draw
     */
    private static List<Pair> MOVES_TO_DRAW = new ArrayList<>();

    static {
        MOVES_P1_WINS.add(new Pair(0, 0)); // Player 1
        MOVES_P1_WINS.add(new Pair(0, 1)); // Player 2
        MOVES_P1_WINS.add(new Pair(1, 1)); // Player 1
        MOVES_P1_WINS.add(new Pair(0, 2)); // Player 2
        MOVES_P1_WINS.add(new Pair(2, 2)); // Player 1

        MOVES_TO_DRAW.add(new Pair(0, 0)); // Player 1
        MOVES_TO_DRAW.add(new Pair(0, 1)); // Player 2
        MOVES_TO_DRAW.add(new Pair(1, 0)); // Player 1
        MOVES_TO_DRAW.add(new Pair(1, 1)); // Player 2
        MOVES_TO_DRAW.add(new Pair(2, 1)); // Player 1
        MOVES_TO_DRAW.add(new Pair(2, 0)); // Player 2
        MOVES_TO_DRAW.add(new Pair(0, 2)); // Player 1
        MOVES_TO_DRAW.add(new Pair(1, 2)); // Player 2
        MOVES_TO_DRAW.add(new Pair(2, 2)); // Player 1
    }

    @Mock
    private ITicTacToeView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        this.presenter = new TicTacToePresenter(view);
        this.winner = new Player("Player1", 1);
    }

    @Test
    public void isGameInProgress_ok() {
        // Given

        // When
        boolean gameInProgress = this.presenter.isGameInProgress();

        // Then
        assertThat(gameInProgress).isTrue();
    }

    @Test
    public void setCell_validMove(){
        // Given

        // When
        this.presenter.setCell(0, 0);

        // Then
        Mockito.verify(view, Mockito.only()).setCellImage(Player.RES_PLAYER_1);
    }

    @Test
    public void setCell_cellAlreadyInUse(){
        // Given
        this.presenter.setCell(0, 0);
        Mockito.verify(view, Mockito.only()).setCellImage(Player.RES_PLAYER_1);

        // When
        this.presenter.setCell(0, 0);

        // Then
        // the view was not updated after setCell
        Mockito.verify(view, Mockito.never()).setCellImage(Player.RES_PLAYER_2);

        // there is no winner yet
        Mockito.verify(view, Mockito.never()).showWinner(winner);

        // neither draw
        Mockito.verify(view, Mockito.never()).showDraw();
    }


    @Test
    public void setCell_showWinner(){
        // Given

        // When
        // do moves for player 1 becomes winner
        do_moves(MOVES_P1_WINS);

        // Then
        Mockito.verify(view, Mockito.atLeastOnce()).showWinner(winner);
    }

    @Test
    public void setCell_showDraw(){
        do_moves(MOVES_TO_DRAW);

        Mockito.verify(view, Mockito.atLeastOnce()).showDraw();
    }

    @Test
    public void restartGame_clearScreen() {
        this.presenter.restartGame();

        Mockito.verify(view, Mockito.only()).clearScreen();
    }


    @Test
    public void listMoves2Win(){
        assertEquals(MOVES_P1_WINS.get(0), new Pair(0,0));
        assertEquals(MOVES_P1_WINS.get(1), new Pair(0,1));
        assertEquals(MOVES_P1_WINS.get(2), new Pair(1,1));
        assertEquals(MOVES_P1_WINS.get(3), new Pair(0,2));
        assertEquals(MOVES_P1_WINS.get(4), new Pair(2,2));
    }

    @Test
    public void listMoves2Draw(){
        assertEquals(MOVES_TO_DRAW.get(0), new Pair(0,0));
        assertEquals(MOVES_TO_DRAW.get(1), new Pair(0,1));
        assertEquals(MOVES_TO_DRAW.get(2), new Pair(1,0));
        assertEquals(MOVES_TO_DRAW.get(3), new Pair(1,1));
        assertEquals(MOVES_TO_DRAW.get(4), new Pair(2,1));
        assertEquals(MOVES_TO_DRAW.get(5), new Pair(2,0));
        assertEquals(MOVES_TO_DRAW.get(6), new Pair(0,2));
        assertEquals(MOVES_TO_DRAW.get(7), new Pair(1,2));
        assertEquals(MOVES_TO_DRAW.get(8), new Pair(2,2));
    }

    private void do_moves(List<Pair> moves){
        moves.forEach(pair -> this.presenter.setCell((Integer)pair.first, (Integer) pair.second));
    }
}
