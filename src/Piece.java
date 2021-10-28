package src;

import java.lang.reflect.Array;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */

public abstract class Piece {

    /*
     * This class defines the Piece object which is the framework for every chess
     * piece
     */
    public String pieceID;
    private final boolean isWhite;
    private boolean isTaken;
    public Tile currentTile;

    /**
     * 
     * @param isWhite     boolean that defines if a piece is white or black
     * @param currentTile to determine what the currently selected tile is for valid
     *                    move checking
     */
    public Piece(boolean isWhite, Tile currentTile) {
        /**
         * Constructor for Piece object.
         */
        this.isWhite = isWhite;
        this.currentTile = currentTile;
        isTaken = true;
        pieceID = "";

    }

    /**
     * getter for Piece ID
     * 
     * @return returns String PieceID
     */
    public String getPieceID() {
        /**
         * getter for Piece ID
         */
        return pieceID;
    }

    /**
     * This method is overrided in each piece class
     * 
     * @param targetTile defines what tile is being attacked or checked for
     * @return returns true if move is valid
     */
    public boolean isMoveValid(Tile targetTile) {
        /**
         * 
         */
        return true;
    }

    /**
     * Function to move piece, replaces old tile with empty tile if move is
     * successful.
     * 
     * @param targetTile defines what tile is being attacked or checked for
     */

    public void movePiece(Tile targetTile) {

        targetTile.setPiece(this);
        currentTile.setPiece(null);
        currentTile = targetTile;

    }

    /**
     * getter for piece color
     * 
     * @return returns boolean for piece color
     */
    public boolean getIsWhite() {
        return isWhite;
    }

    /**
     * getter for if piece is taken
     * 
     * @return returns boolean for if piece is taken
     */
    public boolean getIsTaken() {

        return isTaken;
    }

    public void setIsTaken(Boolean bool) {
        this.isTaken = bool;
    }

    /**
     * function to check if piece has moved
     * 
     * @return returns boolean hasMoved.
     */
    public boolean getHasMoved() {
        return true;
    }

    // private Tile getEnemyKing() {
    // if (!isWhite) {
    // return currentTile.getBoard().getWhiteKing();
    // } else {
    // return currentTile.getBoard().getBlackKing();
    // }
    // }

    // private Tile getOwnKing(){
    // if(isWhite){
    // return currentTile.getBoard().getWhiteKing();
    // } else{
    // return currentTile.getBoard().getBlackKing();
    // }
    // }

}