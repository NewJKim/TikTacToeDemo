package org.example;

// Player.java
public abstract class Player {
    protected final String name;
    protected final Mark mark;

    protected Player(String name, Mark mark) {
        this.name = name;
        this.mark = mark;
    }

    public String name() { return name; }
    public Mark mark() { return mark; }

    public abstract int[] chooseMove(Board board);
}