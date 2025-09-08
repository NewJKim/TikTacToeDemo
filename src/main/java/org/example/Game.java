package org.example;

// Game.java
public class Game {
    private final Board board = new Board();
    private final Player p1;
    private final Player p2;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void play() {
        Player current = p1;
        while (true) {
            board.print();
            int[] move = current.chooseMove(board);  // polymorphism!
            board.place(move[0], move[1], current.mark());

            if (board.winner() != Mark.EMPTY) {
                board.print();
                System.out.println("Winner: " + current.name());
                break;
            }
            if (board.isFull()) {
                board.print();
                System.out.println("Draw!");
                break;
            }
            current = (current == p1) ? p2 : p1;
        }
    }
}
