package org.example;

// HumanPlayer.java
import java.util.Scanner;

public class HumanPlayer extends Player {
    private final Scanner in;

    public HumanPlayer(String name, Mark mark, Scanner in) {
        super(name, mark);
        this.in = in;
    }

    @Override
    public int[] chooseMove(Board board) {
        while (true) {
            System.out.printf("%s (%s), enter row and col [1-3 1-3]: ", name, mark);
            int r = in.nextInt() - 1;
            int c = in.nextInt() - 1;
            if (board.isEmpty(r, c)) return new int[]{r, c};
            System.out.println("Invalid move, try again.");
        }
    }
}