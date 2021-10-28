package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * "i think so isn't it crazy like how they taste you think. their is like the a
 * premium premium that only has 1 thing of grapes grow a year"
 * 
 * This Chess implementation creates a command line chess game that allows two
 * human players to play chess using command line inputs representing the
 * location of each tile to pick moves
 * 
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 * @Version 1.0
 * @Since 10/22/21
 * 
 */
public class Main {
    public static void main(String args[]) {

        Board gameBoard = new Board();
        gameBoard.setBoard();
        // Board dupeBoard = gameBoard.clone();
        // System.out.println("Clone Board: ");
        // dupeBoard.printBoard();
        // System.out.println("Real Board: ");

        Player whitePlayer = new Player(true, gameBoard);
        Player blackPlayer = new Player(false, gameBoard);

        boolean whiteMoveSuccess;
        boolean blackMoveSuccess;

        while (true) {
            whiteMoveSuccess = false;
            blackMoveSuccess = false;
            // System.out.println("Clone Board: ");
            // dupeBoard.printBoard();
            // System.out.println("Real Board: ");
            gameBoard.printBoard();
            while (!whiteMoveSuccess) {
                whiteMoveSuccess = whitePlayer.attemptMove();
                for (int row = 0; row < 8; row++) {
                    gameBoard.tiles[row][5].resetVulnerableTile();
                }
            }
            gameBoard.printBoard();
            while (!blackMoveSuccess) {
                blackMoveSuccess = blackPlayer.attemptMove();
                for (int row = 0; row < 8; row++) {
                    gameBoard.tiles[row][2].resetVulnerableTile();
                }
            }
        }
    }
}