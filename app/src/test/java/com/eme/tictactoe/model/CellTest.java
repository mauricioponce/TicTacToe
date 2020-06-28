package com.eme.tictactoe.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class CellTest {

    private Cell cell;

    @Before
    public void setUp() {
        Player player = new Player("Jonas", 1);

        cell = new Cell(player);
    }

    @Test
    public void isEmpty() {
        //Given

        // When
        boolean result = cell.isEmpty();

        // Then
        assertFalse(result);
    }
}