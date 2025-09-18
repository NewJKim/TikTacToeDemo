package cpp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;


import java.util.Optional;


class BoardTest {
    @Test
    @DisplayName("First move can be placed and turn is swapped")
    void placeFirstMoveAndSwapTurn() {
        Board b = new Board(3);
        Assertions.assertEquals(Mark.X, b.getCurrentTurn());
        b.place(new Move(0, 0, Mark.X));
        Assertions.assertEquals(Mark.O, b.getCurrentTurn());
        Assertions.assertEquals(Mark.X, b.getCell(0, 0));
    }


    @Test
    @DisplayName("Out of bounds move is rejected")
    void outOfBoundsRejected() {
        Board b = new Board(3);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> b.place(new Move(3, 0, Mark.X)));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> b.place(new Move(0, -1, Mark.X)));
    }

    @Test
    @DisplayName("Wrong turn move is rejected")
    void wrongTurnRejected() {
        Board b = new Board(3);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> b.place(new Move(0, 0, Mark.O)));
    }


    @Test
    @DisplayName("Occupied cell is rejected")
    void occupiedCellRejected() {
        Board b = new Board(3);
        b.place(new Move(0, 0, Mark.X));
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> b.place(new Move(0, 0, Mark.O)));
    }

    @Test
    @DisplayName("Row win is detected")
    void rowWinDetected() {
        Board b = new Board(3);
        b.place(new Move(0,0, Mark.X));
        b.place(new Move(1,0, Mark.O));
        b.place(new Move(0,1, Mark.X));
        b.place(new Move(1,1, Mark.O));
        b.place(new Move(0,2, Mark.X));
        Assertions.assertEquals(Optional.of(Mark.X), b.winner());
    }


    @Test
    @DisplayName("Column win is detected")
    void colWinDetected() {
        Board b = new Board(3);
        b.place(new Move(0,2, Mark.X));
        b.place(new Move(0,0, Mark.O));
        b.place(new Move(1,2, Mark.X));
        b.place(new Move(1,0, Mark.O));
        b.place(new Move(2,1, Mark.X));
        b.place(new Move(2,0, Mark.O));
        Assertions.assertEquals(Optional.of(Mark.O), b.winner());
    }

    @Test
    @DisplayName("Main diagonal win is detected")
    void mainDiagWinDetected() {
        Board b = new Board(3);
        b.place(new Move(0,0, Mark.X));
        b.place(new Move(0,1, Mark.O));
        b.place(new Move(1,1, Mark.X));
        b.place(new Move(0,2, Mark.O));
        b.place(new Move(2,2, Mark.X));
        Assertions.assertEquals(Optional.of(Mark.X), b.winner());
    }


    @Test
    @DisplayName("Anti diagonal win is detected")
    void antiDiagWinDetected() {
        Board b = new Board(3);
        b.place(new Move(0,2, Mark.X));
        b.place(new Move(0,0, Mark.O));
        b.place(new Move(1,1, Mark.X));
        b.place(new Move(1,0, Mark.O));
        b.place(new Move(2,0, Mark.X));
        Assertions.assertEquals(Optional.of(Mark.X), b.winner());
    }

    @Test
    @DisplayName("Draw is detected")
    void drawDetectedByFullBoardAndNoWinner() {
        Board b = new Board(3);
// Fill the board with no winner
        b.place(new Move(0,0, Mark.X));
        b.place(new Move(0,1, Mark.O));
        b.place(new Move(0,2, Mark.X));
        b.place(new Move(1,2, Mark.O));
        b.place(new Move(1,0, Mark.X));
        b.place(new Move(2,0, Mark.O));
        b.place(new Move(1,1, Mark.X));
        b.place(new Move(2,2, Mark.O));
        b.place(new Move(2,1, Mark.X));


        Assertions.assertTrue(b.isFull());
        Assertions.assertEquals(Optional.empty(), b.winner());
    }
}