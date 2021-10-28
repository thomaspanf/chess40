package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class Player {
    /**
     * This class is used to define the Player object
     */
    Board board;

    public boolean whiteSide;
    private boolean requestedDraw;

    /**
     * 
     * @param whiteSide defines which color the player is in control of
     * @param board     defines board object being referenced by the player
     */
    public Player(boolean whiteSide, Board board) {
        this.whiteSide = whiteSide;
        this.board = board;
        requestedDraw = false;
    }

    /**
     * 
     * @return returns 1 if player is on white side, 0 if player is black side
     */
    public boolean isWhiteSide() {
        return whiteSide;
    }

    public ArrayList<Piece> getAllWhitePieces(Board board) {
        ArrayList<Piece> list = new ArrayList<Piece>();
        // System.out.println(list.size());
        for (Tile a[] : board.tiles) {
            for (Tile t : a) {
                // System.out.println(t.getPiece());
                if ((t.getPiece() instanceof Piece) && (t.getPiece().getIsWhite())) {
                    list.add(t.getPiece());
                }
            }
        }
        return list;
    }

    public ArrayList<Piece> getAllBlackPieces(Board board) {
        ArrayList<Piece> list = new ArrayList<Piece>();
        // System.out.println(list.size());
        for (Tile a[] : board.tiles) {
            for (Tile t : a) {
                // System.out.println(t.getPiece());
                if ((t.getPiece() instanceof Piece) && !(t.getPiece().getIsWhite())) {
                    list.add(t.getPiece());
                }
            }
        }
        return list;
    }

    public boolean isCheck(Board board) {
        ArrayList<Piece> whitePieces = getAllWhitePieces(board);
        ArrayList<Piece> blackPieces = getAllBlackPieces(board);
        Piece king = null;

        if (isWhiteSide()) {
            for (Piece p : blackPieces) {
                if (p instanceof King) {
                    king = p;
                }
            }
            for (int i = 0; i < whitePieces.size(); i++) {
                // System.out.println(king.pieceID);
                if (whitePieces.get(i).isMoveValid(king.currentTile)) {
                    // System.out.println(t.getPiece().getPieceID());
                    // System.out.println("Check");
                    return true;
                }
            }
        } else {
            for (Piece p : whitePieces) {
                if (p instanceof King) {
                    king = p;
                }
            }
            for (int i = 0; i < blackPieces.size(); i++) {
                // System.out.println(king.pieceID);
                // System.out.println(i);
                // System.out.println(blackPieces.get(i).getPieceID());
                if (blackPieces.get(i).isMoveValid(king.currentTile)) {
                    // System.out.println(t.getPiece().getPieceID());
                    // System.out.println("Check");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheckMove(Board board, Tile targetTile, Piece currentPiece) {
        ArrayList<Piece> whitePieces = getAllWhitePieces(board);
        ArrayList<Piece> blackPieces = getAllBlackPieces(board);
        Piece king = null;

        if (!(currentPiece instanceof King)) {
            return false;
        }

        if (isWhiteSide()) {
            for (Piece p : whitePieces) {
                if (p instanceof King) {
                    king = p;
                }
            }
            for (int i = 0; i < blackPieces.size(); i++) {
                // System.out.println(king.pieceID);
                if (blackPieces.get(i).isMoveValid(targetTile)) {
                    // System.out.println(t.getPiece().getPieceID());
                    // System.out.println("Check");
                    return true;
                }
            }

        } else {
            for (Piece p : blackPieces) {
                if (p instanceof King) {
                    king = p;
                }
            }
            for (int i = 0; i < whitePieces.size(); i++) {
                // System.out.println(king.pieceID);
                if (whitePieces.get(i).isMoveValid(targetTile)) {
                    // System.out.println(t.getPiece().getPieceID());
                    // System.out.println("Check");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkPin(Board board) {
        ArrayList<Piece> whitePieces = getAllWhitePieces(board);
        ArrayList<Piece> blackPieces = getAllBlackPieces(board);
        Piece king = null;
        Boolean isChecked = false;
        // System.out.println(x + ", " + y);

        if (isWhiteSide()) {
            for (Piece p : whitePieces) {
                if (p instanceof King) {
                    king = p;
                }
            }
            for (int i = 0; i < blackPieces.size(); i++) {
                // System.out.println(king.pieceID);
                if (blackPieces.get(i).isMoveValid(king.currentTile)) {
                    // System.out.println(t.getPiece().getPieceID());
                    // System.out.println("Check");
                    return true;
                }
            }
        } else {
            for (Piece p : blackPieces) {
                if (p instanceof King) {
                    king = p;
                }
            }
            for (int i = 0; i < whitePieces.size(); i++) {
                // System.out.println(king.pieceID);
                if (whitePieces.get(i).isMoveValid(king.currentTile)) {
                    // System.out.println(t.getPiece().getPieceID());
                    // System.out.println("Check");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isCheckmate(Board board) {
        Board dupeBoard = board.clone();
        ArrayList<Piece> myPieces = !isWhiteSide() ? getAllWhitePieces(dupeBoard) : getAllBlackPieces(dupeBoard);
        // System.out.println("Clone Board: ");
        // dupeBoard.printBoard();

        // loop through all the tiles
        for (Tile t[] : dupeBoard.tiles) {
            for (Tile currentTitle : t) {
                // loop through all the pieces
                for (int i = 0; i < myPieces.size(); i++) {
                    final int originalY = myPieces.get(i).currentTile.getY();
                    final int originalX = myPieces.get(i).currentTile.getX();
                    // if(myPieces.get(i) instanceof King){
                    // for (int xOffset = -1; xOffset <= 1; xOffset++) {
                    // for (int yOffset = -1; yOffset <=1 ; yOffset++) {
                    // //check if the offset is valid tile
                    // //Tile potentialKingTile = new Tile(kingX + xOffset, kingY + yOffset,
                    // enemyKing.getPiece(), currentTile.getBoard());
                    // int potKingX = originalX + xOffset;
                    // int potKingY = originalY + yOffset;
                    // if((potKingX < 0) || (potKingX > 7) || (potKingY < 0) || (potKingY > 7)){
                    // continue;
                    // }
                    // if(currentTitle.getBoard().getTile(originalX + xOffset, originalY +
                    // yOffset).getPiece()==null){
                    // myPieces.get(i).movePiece(currentTitle.getBoard().getTile(originalX +
                    // xOffset, originalY + yOffset));
                    // //System.out.println("printing checkmate board");
                    // //currentTile.getBoard().printBoard();
                    // if(!isCheck(dupeBoard)){
                    // myPieces.get(i).movePiece(currentTitle.getBoard().getTile(originalX,
                    // originalY));
                    // return false;
                    // }
                    // }
                    // }
                    // }
                    // }
                    if (myPieces.get(i).isMoveValid(currentTitle)) {

                        Piece takenPiece = currentTitle.getPiece();
                        myPieces.get(i).movePiece(currentTitle);
                        // System.out.println(myPieces.get(i).getPieceID());
                        if (!isCheck(dupeBoard)) {
                            System.out.println(currentTitle.getPiece().getPieceID() + ": " + currentTitle.getX() + ", "
                                    + currentTitle.getY());
                            myPieces.get(i).movePiece(dupeBoard.getTile(originalX, originalY));
                            if (takenPiece != null) {
                                takenPiece.movePiece(currentTitle);
                                takenPiece.setIsTaken(false);
                            }
                            // System.out.println(myPieces.get(i).getPieceID());
                            return false;
                        }
                        // Reset the dupe board to use on the next iteration
                        myPieces.get(i).movePiece(dupeBoard.getTile(originalX, originalY));
                        if (takenPiece != null) {
                            takenPiece.movePiece(currentTitle);
                            takenPiece.setIsTaken(false);
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * This method prompts the player to input their move and then stores the input
     * as a String input
     * 
     * @return returns String input
     */
    public String requestInput() {
        if (whiteSide) {
            System.out.print("White's move: ");
        } else {
            System.out.print("Black's move: ");
        }
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    /**
     * This function parses the player input into an x1,y2 and x2,y2 coordinate,
     * then checks if the input and move is valid. If so the piece will be moved. If
     * not a valid input or move, the function will prompt the user to enter a legal
     * move. This function also handles inputs to deal with draws and resignation.
     * If an input is of size 5, the last letter is used to determine what piece to
     * upgrade to. If piece upgrade is being requested, promotePiece() is called.
     * 
     * @return returns 1 if move is successful, 0 if not successful.
     */
    public boolean attemptMove() {
        String input = requestInput();
        if (input.contains("draw?")) {
            requestedDraw = true;
            return true;
        } else if (input.equals("draw")) {
            System.exit(0);
            return false;
        } else
            ;

        if (input.contains("resign")) {
            if (isWhiteSide()) {
                System.out.println("Black wins");
                System.exit(0);
            } else {
                System.out.println("White wins");
                System.exit(0);
            }
            return false;
        }

        input = input.replaceAll("\\s+", "");

        if (input.contains("draw?")) {
            if (requestDraw())
                ;
            // System.out.println("game end");
            return true;
        }

        if (input.length() == 5) {
            char promoType = input.charAt(4);
            promotePiece(promoType, input);
            System.out.println("Promotion Piece Type: " + promoType);
            return true;
        }

        if ((input.length() != 4)) {
            System.out.println("Illegal Move, try again");
            return false;
        }

        String invalidChars = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
        for (int i = 0; i < invalidChars.length(); i++) {
            if (input.contains(Character.toString(invalidChars.charAt(i)))) {
                System.out.println("Illegal Move, try again");
                return false;
            }
        }

        int y1 = Integer.parseInt("" + input.charAt(1)) - 1;
        int y2 = Integer.parseInt("" + input.charAt(3)) - 1;

        if (y1 > 7 || y2 > 7) {
            System.out.println("Illegal Move, try again");
            return false;
        }

        String constString = "abcdefgh";

        int x1 = constString.indexOf("" + input.charAt(0));
        int x2 = constString.indexOf("" + input.charAt(2));

        Tile currentTile = board.getTile(x1, y1);
        Tile targetTile = board.getTile(x2, y2);

        // ArrayList<Piece> whitePieces = getAllWhitePieces();
        // ArrayList<Piece> blackPieces = getAllBlackPieces();

        Piece currentPiece = currentTile.getPiece();

        if ((currentPiece == null) || (currentPiece.getIsWhite() != whiteSide)) {

            System.out.println(currentTile.getPiece());
            System.out.println("Illegal Move, try again");
            return false;
        }
        if (!currentPiece.isMoveValid(targetTile)) {
            System.out.println("Illegal Move, try again");
            return false;
        }

        if (isCheckMove(currentTile.getBoard(), targetTile, currentPiece)) {
            System.out.println("Illegal Move, try again");
            return false;
        }

        // if(checkPin(currentTile.getBoard(), currentPiece, targetTile)){
        // System.out.println("Illegal Move, try again 3");
        // return false;
        // }

        currentPiece.movePiece(targetTile);

        if (isWhiteSide() == currentPiece.getIsWhite()) {
            if (checkPin(currentTile.getBoard())) {
                currentPiece.movePiece(currentTile);
                System.out.println("Illegal Move, try again");
                return false;
            }
        }

        if (isCheck(currentTile.getBoard())) {
            if (isCheckmate(currentTile.getBoard())) {
                System.out.println("Checkmate");
                if (isWhiteSide()) {
                    System.out.println("White wins");
                } else {
                    System.out.println("Black wins");
                }
                System.exit(0);
            }
            System.out.println("Check");
        }
        return true;
    }

    /**
     * This function is used to handle the setting of a new piece when a pawn is
     * promoted. After pawn is promoted, the pawn at the location before promotion
     * is deleted.
     * 
     * @param promoType char value that signifies which type of piece is being
     *                  requested as a promotion
     * @param input     string input passed in, used to determine indices of current
     *                  and target piece.
     * @return returns true if piece is successfully promoted
     */
    public boolean promotePiece(char promoType, String input) {
        String constString = "abcdefgh";
        int y1 = Integer.parseInt("" + input.charAt(1)) - 1;
        int y2 = Integer.parseInt("" + input.charAt(3)) - 1;
        int x1 = constString.indexOf("" + input.charAt(0));
        int x2 = constString.indexOf("" + input.charAt(2));

        Tile currentTile = board.getTile(x1, y1);
        Piece currentPiece = currentTile.getPiece();

        if (promoType == 'B' && currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Bishop(true, board.tiles[x2][y2]));
        }
        if (promoType == 'B' && !currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Bishop(false, board.tiles[x2][y2]));
        }
        if (promoType == 'Q' && currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Queen(true, board.tiles[x2][y2]));
        }
        if (promoType == 'Q' && !currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Queen(false, board.tiles[x2][y2]));
        }
        if (promoType == 'R' && currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Rook(true, board.tiles[x2][y2]));
        }
        if (promoType == 'R' && !currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Rook(false, board.tiles[x2][y2]));
        }
        if (promoType == 'N' && currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Knight(true, board.tiles[x2][y2]));
        }
        if (promoType == 'N' && !currentPiece.getIsWhite()) {
            board.tiles[x2][y2].setPiece(new Knight(false, board.tiles[x2][y2]));
        }
        board.tiles[x1][y1].setPiece(null);
        return true;
    }

    /**
     * This function is called when "draw?" is entered as an input by a player. If
     * the opposing player enters "draw", the function returns true and the game
     * will end in a draw.
     * 
     * @return returns true if draw is successful.
     */
    public boolean requestDraw() {
        String input = requestInput();
        if (input.contains("draw"))
            ;
        return true;
    }
}