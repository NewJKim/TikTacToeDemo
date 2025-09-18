package cpp;

public final class Move {
    private final int row;
    private final int col;
    private final Mark mark;


    public Move(int row, int col, Mark mark) {
        if (row < 0 || col < 0) throw new IllegalArgumentException("row/col must be >= 0");
        if (mark == null || mark == Mark.EMPTY) throw new IllegalArgumentException("mark must be X or O");
        this.row = row;
        this.col = col;
        this.mark = mark;
    }


    public int row() { return row; }
    public int col() { return col; }
    public Mark mark() { return mark; }
}