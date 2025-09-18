package cpp;

import java.util.Optional;
import java.util.Scanner;


public class ConsoleApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = new Board(3);
        Player x = new HumanPlayer(Mark.X, in);
        Player o = new RandomAIPlayer(Mark.O);
        Game game = new Game(board, x, o);


        Optional<Mark> winner = game.run();
        if (winner.isPresent()) {
            System.out.println("Winner: " + winner.get());
        } else {
            System.out.println("It's a draw!");
        }
    }
}