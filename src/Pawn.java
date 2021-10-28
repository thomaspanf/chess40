package src;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class Pawn extends Piece {

    private boolean hasMoved;
    private boolean canPromote;

    /**
     * This class is uuses the Piece object to define how a Pawn behaves
     * 
     * @param isWhite flag for white or black piece
     * @param tile    represents tile object
     */
    public Pawn(boolean isWhite, Tile tile) {
        /**
         * Pawn constructor
         */
        super(isWhite, tile);
        if (isWhite) {
            pieceID = "wP";
        } else {
            pieceID = "bP";
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

        int currentX = currentTile.getX();
        int currentY = currentTile.getY();
        int targetX = targetTile.getX();
        int targetY = targetTile.getY();

        int yDistance = Math.abs(currentTile.getY() - targetTile.getY());
        int xDistance = Math.abs(currentTile.getX() - targetTile.getX());
        int yOffSet;

        // System.out.println(currentX + ", " + currentY + " : " + targetX + ", " +
        // targetY);

        try {
            yOffSet = (targetTile.getY() - currentTile.getY()) / Math.abs((targetTile.getY() - currentTile.getY()));
        } catch (Exception e) {
            yOffSet = (targetTile.getY() - currentTile.getY());
            // TODO: handle exception
        }

        if (targetY == 0 || targetY == 7) {
            canPromote = true;
            yOffSet = 0;
        } else {
            try {
                yOffSet = (targetTile.getY() - currentTile.getY()) / Math.abs((targetTile.getY() - currentTile.getY()));
            } catch (Exception e) {
                yOffSet = (targetTile.getY() - currentTile.getY());
                // TODO: handle exception
            }
        }

        if (!isValidTile(currentTile, targetTile)) {
            return false;
        }

        if (getIsWhite()) {
            if (targetY < currentY) {
                return false;
            }
        } else if (targetY > currentY) {
            return false;
        }
        /**
         * condition for diagonal moves and resets en passant flag.
         */
        if (xDistance == 1 && yDistance == 1) {
            if (targetTile.getPiece() != null && targetTile.getPiece().getIsWhite() != this.getIsWhite()) {
                return true;
            }
            if (targetTile.getPiece() == null && targetTile.getIsVulnerableTile()) {
                targetTile.getBoard().getTile(currentX, targetY - yOffSet).setPiece(null);
                ;
                // System.out.println("!");
                return true;
            } else {
                // System.out.println("!!!");
                return false;
            }
        }

        if (yDistance == 1 && targetTile.getPiece() != null) {
            // System.out.println("poop");
            return false;
        }

        if (yDistance == 2) {
            if (targetTile.getPiece() != null || targetTile.getBoard().getTile(currentX, targetY).getPiece() != null) {
                // System.out.println(currentX + ", " + targetY + "+" + yOffSet);

                return false;
            }
            return true;
        }
        return true;
    }

    /**
     * In the pawn implementation of movePiece, conditions for en passant and
     * creating a tile that may be attacked under en passant are handled here.
     * 
     * @param targetTile used in movePiece to determine where a piece should be
     *                   moved
     */
    @Override
    public void movePiece(Tile targetTile) {

        int yDistance = Math.abs(currentTile.getY() - targetTile.getY());
        int currentX = currentTile.getX();
        int targetX = targetTile.getX();
        int targetY = targetTile.getY();
        int yOffSet;
        try {
            yOffSet = (targetTile.getY() - currentTile.getY()) / Math.abs((targetTile.getY() - currentTile.getY()));
        } catch (Exception e) {
            yOffSet = 0;
            // TODO: handle exception
        }
        if (yDistance == 2) {
            currentTile.getBoard().getTile(currentX, targetY - yOffSet).setVulnerableTile();
        }
        /**
         * after a pawn makes a move, the vulnerable tile flag is reset.
         */
        if (yDistance == 1) {
            currentTile.getBoard().getTile(currentX, targetY - (yOffSet * 2)).resetVulnerableTile();
        }

        targetTile.setPiece(this);
        currentTile.setPiece(null);
        currentTile = targetTile;
        hasMoved = true;
        /**
         * if pawn can promote and is at end of board, sets promotion to Queen if no
         * input is given.
         */
        if (canPromote && (targetY == 0 || targetY == 7)) {
            currentTile.getBoard().getTile(currentX, targetY).setPiece(
                    new Queen(currentTile.getPiece().getIsWhite(), currentTile.getBoard().getTile(currentX, targetY)));
        }
    }

    /**
     * This function checks if the target tile requested by player is a valid tile
     * for a pawn to move to.
     * 
     * @param current represents the current tile being checked by isValidTile
     * @param target  represents the target tile being checked by isValidTile
     * @return returns 1 if target is on a valid tile to move to, 0 if target is not
     *         on a valid tile to move to
     */
    public boolean isValidTile(Tile current, Tile target) {

        int yDistance = Math.abs(current.getY() - target.getY());
        int xDistance = Math.abs(current.getX() - target.getX());

        if (hasMoved) {
            return ((yDistance == 1 && xDistance == 0) || (yDistance == 1 && xDistance == 1));

        } else {
            return ((yDistance == 1 && xDistance == 0) || (yDistance == 2 && xDistance == 0)
                    || (yDistance == 1 && xDistance == 1));
        }
    }

}