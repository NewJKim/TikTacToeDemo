package cpp;

import java.util.Optional;
import java.util.Objects;


public class Board {
    private final int size;
    private final Mark[][] grid;
    private Mark currentTurn = Mark.X; // X starts


    public Board(int size) {
        if (size < 3) throw new IllegalArgumentException("size must be >= 3");
        this.size = size;
        this.grid = new Mark[size][size];
        reset();
    }


    public Board() { this(3); }


    public void reset() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                grid[r][c] = Mark.EMPTY;
            }
        }
        currentTurn = Mark.X;
    }


    public int size() { return size; }


    public Mark getCell(int r, int c) {
        validateIndex(r, c);
        return grid[r][c];
    }


    public Mark getCurrentTurn() { return currentTurn; }


    public boolean isFull() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (grid[r][c] == Mark.EMPTY) return false;
            }
        }
        return true;
    }

    public void place(Move mv) {
        Objects.requireNonNull(mv, "move");
        validateIndex(mv.row(), mv.col());
        if (mv.mark() != currentTurn)
            throw new IllegalArgumentException("Not " + mv.mark() + "'s turn");
        if (grid[mv.row()][mv.col()] != Mark.EMPTY)
            throw new IllegalArgumentException("Cell already occupied");
        grid[mv.row()][mv.col()] = mv.mark();
        swapTurn();
    }


    private void swapTurn() {
        currentTurn = (currentTurn == Mark.X) ? Mark.O : Mark.X;
    }


    public Optional<Mark> winner() {
        // rows
        for (int r = 0; r < size; r++) {
            Mark m = grid[r][0];
            if (m != Mark.EMPTY) {
                boolean all = true;
                for (int c = 1; c < size; c++) if (grid[r][c] != m) { all = false; break; }
                if (all) return Optional.of(m);
            }
        }
        // cols
        for (int c = 0; c < size; c++) {
            Mark m = grid[0][c];
            if (m != Mark.EMPTY) {
                boolean all = true;
                for (int r = 1; r < size; r++) if (grid[r][c] != m) { all = false; break; }
                if (all) return Optional.of(m);
            }
        }
        // main diag
        Mark m = grid[0][0];
        if (m != Mark.EMPTY) {
            boolean all = true;
            for (int i = 1; i < size; i++) if (grid[i][i] != m) { all = false; break; }
            if (all) return Optional.of(m);
        }
        // anti diag
        m = grid[0][size - 1];
        if (m != Mark.EMPTY) {
            boolean all = true;
            for (int i = 1; i < size; i++) if (grid[i][size - 1 - i] != m) { all = false; break; }
            if (all) return Optional.of(m);
        }
        return Optional.empty();
    }


    private void validateIndex(int r, int c) {
        if (r < 0 || r >= size || c < 0 || c >= size)
            throw new IllegalArgumentException("Index out of bounds");
    }
}