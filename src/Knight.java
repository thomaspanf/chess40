package src;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class Knight extends Piece {
    /**
     * 
     * @param isWhite flag for white or black piece
     * @param tile    represents tile object
     */

    public Knight(boolean isWhite, Tile tile) {
        /**
         * Knight contructor
         */
        super(isWhite, tile);
        if (isWhite) {
            pieceID = "wN";
        } else {
            pieceID = "bN";
        }
    }

    /**
     * This function is used to check if the player requested move is a valid
     * operation. First it checks if a king is under check and if moving this piece
     * jeapordizes its king, then uses the isValidTile function to check if the
     * requested move is a valid move to make
     * 
     * @param targetTile used to check if moving piece to targetTile is valid
     * @return returns false whenever move is not valid, otherwise returns true
     * 
     */
    @Override
    public boolean isMoveValid(Tile targetTile) {

        if (!isValidTile(currentTile, targetTile)) {

            return false;
        }
        /**
         * checks if target piece is the same color as current piece
         */
        if (targetTile.getPiece() != null && targetTile.getPiece().getIsWhite() == this.getIsWhite()) {
            return false;
        }
        return true;
    }

    /**
     * This function is used to move a knight piece. Only allowed to be called if
     * isMoveValid returns true.
     * 
     * @param current represents the current tile being checked by isValidTile
     * @param target  represents the target tile being checked by isValidTile
     * @return returns 1 if target is on a valid tile to move to, 0 if target is not
     *         on a valid tile to move to
     */
    public boolean isValidTile(Tile current, Tile target) {

        int yDistance = Math.abs(current.getY() - target.getY());
        int xDistance = Math.abs(current.getX() - target.getX());

        return (((yDistance == 1) && (xDistance == 2)) || ((yDistance == 2) && (xDistance == 1)));
    }

}