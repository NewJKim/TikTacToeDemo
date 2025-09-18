package cpp;

public abstract class Player {
    protected final Mark mark;


    protected Player(Mark mark) {
        if (mark == null || mark == Mark.EMPTY) throw new IllegalArgumentException("mark must be X or O");
        this.mark = mark;
    }


    public Mark getMark() { return mark; }


    public abstract Move nextMove(Board board);
}