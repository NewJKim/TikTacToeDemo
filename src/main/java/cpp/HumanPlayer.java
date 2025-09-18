package cpp;

import java.util.InputMismatchException;
import java.util.Scanner;


public class HumanPlayer extends Player {
    private final Scanner in;


    public HumanPlayer(Mark mark, Scanner in) {
        super(mark);
        if (in == null) throw new IllegalArgumentException("Scanner must not be null");
        this.in = in;
    }


    @Override
    public Move nextMove(Board board) {
        while (true) {
            printBoard(board);
            System.out.println("Player " + mark + " — enter row and column (0-" + (board.size()-1) + "):");
            int r, c;
            try {
                r = in.nextInt();
                c = in.nextInt();
            } catch (InputMismatchException ex) {
                in.nextLine(); // clear token
                System.out.println("Please enter two integers like: 0 2");
                continue;
            }
            try {
                if (board.getCell(r, c) != Mark.EMPTY) {
                    System.out.println("That cell is occupied. Try again.");
                    continue;
                }
                return new Move(r, c, mark);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    private void printBoard(Board board) {
        System.out.println();
        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.size(); c++) {
                System.out.print(" " + board.getCell(r, c).toSymbol());
                if (c < board.size()-1) System.out.print(" |");
            }
            System.out.println();
            if (r < board.size()-1) {
                System.out.println("---+---+---".substring(0, 4*board.size()-1));
            }
        }
        System.out.println();
    }
}