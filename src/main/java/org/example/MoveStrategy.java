package org.example;

public interface MoveStrategy {
    int[] pickMove(Board board, Mark mark);
}