package src;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class Rook extends Piece {

    private boolean hasMoved;

    /**
     * This class is uuses the Piece object to define how a Rook behaves
     * 
     * @param isWhite flag for white or black piece
     * @param tile    represents tile object
     */
    public Rook(boolean isWhite, Tile tile) {
        /**
         * Rook constructor
         */
        super(isWhite, tile);
        if (isWhite) {
            pieceID = "wR";
        } else {
            pieceID = "bR";
        }
        hasMoved = false;
    }

    /**
     * This function is used to check if the player requested move is a valid
     * operation. First it checks if a king is under check and if moving this piece
     * jeapordizes its king, then uses the isValidTile function to check if the
     * requested move is a valid move to make
     * 
     * @param targetTile used to check if moving piece to targetTile is valid
     * @return returns false whenever move is not valid, otherwise returns true
     */
    @Override
    public boolean isMoveValid(Tile targetTile) {

        if (!isValidTile(currentTile, targetTile)) {
            return false;
        }
        int xIncrement, yIncrement;
        int xDistance = Math.abs((targetTile.getX() - currentTile.getX()));
        int yDistance = Math.abs((targetTile.getY() - currentTile.getY()));
        if (xDistance == 0) {
            xIncrement = 0;
        } else {
            xIncrement = (targetTile.getX() - currentTile.getX()) / Math.abs((targetTile.getX() - currentTile.getX()));

        }
        if (yDistance == 0) {
            yIncrement = 0;
        } else {
            yIncrement = (targetTile.getY() - currentTile.getY()) / Math.abs((targetTile.getY() - currentTile.getY()));

        }
        int xFlag = currentTile.getX() + xIncrement;
        int yFlag = currentTile.getY() + yIncrement;

        /**
         * checks if there is a piece in the path between currentTile and targetTile
         */
        while ((xFlag != targetTile.getX()) || (yFlag != targetTile.getY())) {
            // System.out.println("xy flags:" + xFlag + ", " + yFlag);
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
     * In the rook implementation of movePiece, conditions for castling are handled
     * here.
     * 
     * @param targetTile used in movePiece to determine where a piece should be
     *                   moved
     */
    @Override
    public void movePiece(Tile targetTile) {

        targetTile.setPiece(this);
        currentTile.setPiece(null);
        currentTile = targetTile;
        hasMoved = true;

    }

    /**
     * This function checks if the target tile requested by player is a valid tile
     * for a rook to move to.
     * 
     * @param current represents the current tile being checked by isValidTile
     * @param target  represents the target tile being checked by isValidTile
     * @return returns 1 if target is on a valid tile to move to, 0 if target is not
     *         on a valid tile to move to
     */
    public boolean isValidTile(Tile current, Tile target) {
        int yDistance = Math.abs(current.getY() - target.getY());
        int xDistance = Math.abs(current.getX() - target.getX());

        return (xDistance == 0 ^ yDistance == 0);

    }

    /**
     * @return returns 1 if piece has moved, 0 if not
     */
    @Override
    public boolean getHasMoved() {
        return hasMoved;
    }

}