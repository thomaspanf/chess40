package src;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class King extends Piece {

    private boolean hasMoved;
    private Tile rookTile;
    private Piece castleRook;
    private Tile kingTile;

    /**
     * This class is uses the Piece object to define how a King behaves
     * 
     * @param isWhite flag for white or black piece
     * @param tile    represents tile object
     */
    public King(boolean isWhite, Tile tile) {
        /**
         * King constructor
         */
        super(isWhite, tile);
        if (isWhite) {
            pieceID = "wK";
        } else {
            pieceID = "bK";
        }
        rookTile = null;
        hasMoved = false;
        castleRook = null;
        kingTile = currentTile;
    }

    /**
     * 
     * This function is used to check if the player requested move is a valid
     * operation. First it checks if a king is under check and if moving this piece
     * jeapordizes its king, then uses the isValidTile function to check if the
     * requested move is a valid move to make
     * 
     * The King implementation of isMoveValid includes castling conditions
     *
     * @param targetTile used to check if moving piece to targetTile is valid
     * @return returns false whenever move is not valid, otherwise returns true
     */
    @Override
    public boolean isMoveValid(Tile targetTile) {
        /**
         * This function is used to check if the player requested move is a valid
         * operation. First it checks if a king is under check and if moving this piece
         * jeapordizes its king, then uses the isValidTile function to check if the
         * requested move is a valid move to make
         * 
         * The King implementation of isMoveValid includes castling conditions
         */
        int xDistance = (targetTile.getX() - currentTile.getX());
        int yDistance = (targetTile.getY() - currentTile.getY());

        if (!isValidTile(currentTile, targetTile)) {
            return false;
        }
        /**
         * checks if target piece is the same color as current piece
         */
        if (targetTile.getPiece() != null && targetTile.getPiece().getIsWhite() == getIsWhite()) {
            return false;
        }
        Piece tempPiece;
        /**
         * condition for King Castling
         */
        if (Math.abs(xDistance) == 2 && !hasMoved) {
            if ((getIsWhite())) {
                if (xDistance == 2) {
                    if (currentTile.getBoard().getTile(5, 0).getPiece() != null
                            || currentTile.getBoard().getTile(6, 0).getPiece() != null) {
                        return false;
                    }
                    tempPiece = currentTile.getBoard().getTile(7, 0).getPiece();
                    rookTile = currentTile.getBoard().getTile(5, 0);
                } else {
                    if (currentTile.getBoard().getTile(1, 0).getPiece() != null
                            || currentTile.getBoard().getTile(2, 0).getPiece() != null
                            || currentTile.getBoard().getTile(3, 0).getPiece() != null) {
                        return false;
                    }
                    tempPiece = currentTile.getBoard().getTile(0, 0).getPiece();
                    rookTile = currentTile.getBoard().getTile(3, 0);
                }
            } else {
                if (xDistance == 2) {
                    if (currentTile.getBoard().getTile(5, 7).getPiece() != null
                            || currentTile.getBoard().getTile(6, 7).getPiece() != null) {
                        return false;
                    }
                    tempPiece = currentTile.getBoard().getTile(7, 7).getPiece();
                    rookTile = currentTile.getBoard().getTile(5, 7);
                } else {
                    if (currentTile.getBoard().getTile(1, 7).getPiece() != null
                            || currentTile.getBoard().getTile(2, 7).getPiece() != null
                            || currentTile.getBoard().getTile(3, 7).getPiece() != null) {
                        return false;
                    }
                    tempPiece = currentTile.getBoard().getTile(0, 7).getPiece();
                    rookTile = currentTile.getBoard().getTile(3, 7);

                }
            }
            if (tempPiece instanceof Rook) {
                if (tempPiece.getHasMoved() || hasMoved) {
                    return false;
                } else {
                    castleRook = tempPiece;
                    return true;
                }
            } else {
                return false;
            }
        }

        // if(isCheckedHelper(targetTile)){
        // return false;
        // }

        return true;
    }

    /**
     * This function is used to move a king piece. Only allowed to be called if
     * isMoveValid returns true. In the king implementation of movePiece, castling
     * conditions are included in this function.
     * 
     * @param targetTile used in movePiece to determine where a piece should be
     *                   moved
     */
    @Override
    public void movePiece(Tile targetTile) {

        // System.out.println(hasMoved);
        if (!hasMoved && (castleRook instanceof Rook)) {
            Tile initialTile = castleRook.currentTile;
            // System.out.println(initialTile.getX() + ", " + initialTile.getY());
            castleRook.movePiece(rookTile);
            initialTile.setPiece(null);
        }
        targetTile.setPiece(this);
        currentTile.setPiece(null);
        currentTile = targetTile;
        hasMoved = true;

    }

    /**
     * This function checks if the target tile requested by player is a valid tile
     * for a king to move to.
     * 
     * @param current represents the current tile being checked by isValidTile
     * @param target  represents the target tile being checked by isValidTile
     * @return returns 1 if target is on a valid tile to move to, 0 if target is not
     *         on a valid tile to move to
     */
    public boolean isValidTile(Tile current, Tile target) {

        int yDistance = Math.abs(current.getY() - target.getY());
        int xDistance = Math.abs(current.getX() - target.getX());
        // System.out.println(xDistance + ", " + yDistance);
        if (!hasMoved && xDistance == 2 && yDistance == 0) {
            return true;
        }
        return ((yDistance == 1 && xDistance == 0) || (yDistance == 0 && xDistance == 1)
                || (yDistance == 1 && xDistance == 1));
    }

}