package org.example;

// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Player human = new HumanPlayer("You", Mark.X, in);
        Player ai = new AIPlayer("Computer", Mark.O, new RandomStrategy());

        Game game = new Game(human, ai);
        game.play();
    }
}
