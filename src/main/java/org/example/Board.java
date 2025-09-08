package org.example;

// Board.java
import java.util.Arrays;

public class Board {
    private final Mark[][] grid = new Mark[3][3];
    private int moves = 0;

    public Board() {
        for (Mark[] row : grid) Arrays.fill(row, Mark.EMPTY);
    }

    public boolean isEmpty(int r, int c) { return grid[r][c] == Mark.EMPTY; }

    public boolean place(int r, int c, Mark mark) {
        if (!isEmpty(r, c)) return false;
        grid[r][c] = mark;
        moves++;
        return true;
    }

    public boolean isFull() { return moves == 9; }

    public Mark winner() {
        for (int i = 0; i < 3; i++) {
            if (line(grid[i][0], grid[i][1], grid[i][2])) return grid[i][0];
            if (line(grid[0][i], grid[1][i], grid[2][i])) return grid[0][i];
        }
        if (line(grid[0][0], grid[1][1], grid[2][2])) return grid[0][0];
        if (line(grid[0][2], grid[1][1], grid[2][0])) return grid[0][2];
        return Mark.EMPTY;
    }

    private boolean line(Mark a, Mark b, Mark c) {
        return a != Mark.EMPTY && a == b && b == c;
    }

    public void print() {
        for (int r = 0; r < 3; r++) {
            System.out.printf(" %s | %s | %s %n", grid[r][0], grid[r][1], grid[r][2]);
            if (r < 2) System.out.println("---+---+---");
        }
    }
}