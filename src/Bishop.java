package src;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class Bishop extends Piece {
    /**
     * This class uses the Piece object to define how a Bishop behaves
     * 
     * @param isWhite flag for white or black piece
     * @param tile    represents tile object
     */
    public Bishop(boolean isWhite, Tile tile) {
        /**
         * Bishop constructor
         */
        super(isWhite, tile);
        if (isWhite) {
            pieceID = "wB";
        } else {
            pieceID = "bB";
        }
    }

    /**
     * This function is used to check if the player requested move is a valid
     * operation. First it checks if a king is under check and if moving this piece
     * jeapordizes its king, then uses the isDiag function to check if the requested
     * move is on a diagonal from the current tile.
     * 
     * @param targetTile used to check if moving piece to targetTile is valid
     * @return returns false whenever move is not valid, otherwise returns true
     */
    @Override
    public boolean isMoveValid(Tile targetTile) {

        if (!isDiag(currentTile, targetTile) || (targetTile.getX() - currentTile.getX() == 0)) {
            return false;
        }
        /**
         * used to check which direction to step in
         */
        int xIncrement = (targetTile.getX() - currentTile.getX()) / Math.abs((targetTile.getX() - currentTile.getX()));
        int yIncrement = (targetTile.getY() - currentTile.getY()) / Math.abs((targetTile.getY() - currentTile.getY()));

        int xFlag = currentTile.getX() + xIncrement;
        int yFlag = currentTile.getY() + yIncrement;
        /**
         * checks if there is a piece in the path between currentTile and targetTile
         */
        while ((xFlag != targetTile.getX()) && (yFlag != targetTile.getY())) {
            if (currentTile.getBoard().getTile(xFlag, yFlag).getPiece() != null) {
                return false;
            }
            xFlag += xIncrement;
            yFlag += yIncrement;
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
     * This function determines if the target tile is on a diagonal from the current
     * tile diagonals occur when abs val distance between x and y value are equal
     * 
     * @param current represents the current tile being checked by isValidTile
     * @param target  represents the target tile being checked by isValidTile
     * @return returns 1 if target is on a diagonal, 0 if target is not on diagonal
     */
    public boolean isDiag(Tile current, Tile target) {

        return (Math.abs(current.getX() - target.getX()) == Math.abs(current.getY() - target.getY()));

    }

}
