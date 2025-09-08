package org.example;

// RandomStrategy.java
import java.util.*;

public class RandomStrategy implements MoveStrategy {
    private final Random rng = new Random();

    @Override
    public int[] pickMove(Board board, Mark mark) {
        List<int[]> moves = new ArrayList<>();
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board.isEmpty(r, c)) moves.add(new int[]{r, c});
        return moves.get(rng.nextInt(moves.size()));
    }
}