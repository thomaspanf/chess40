package src;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class Board {
    /**
     * This class is used to initialize a Board of 64 tiles Provides method to get
     * tile information at specified location, method to print gameboard, and method
     * to set the default board.
     * 
     */
    public Tile[][] tiles;

    /**
     * Board constructor. Initilizes all 64 tiles of the board.
     */
    public Board() {
        tiles = new Tile[8][8];
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                tiles[x][y] = new Tile(x, y, null, this);
            }
        }
    }

    /**
     * 
     * @param x horizontal coordinates
     * @param y vertical coordinates this method is used to get information for a
     *          specific tile
     * @return returns tile object at specified location
     */
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    /**
     * This function is used to get the location of the white king
     * 
     * @return always returns null
     */
    public Tile getWhiteKing() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getTile(x, y).getPiece() instanceof King) {
                    if (getTile(x, y).getPiece().getIsWhite()) {
                        return getTile(x, y);
                    }
                }
            }
        }
        return null;
    }

    /**
     * This function is used to get the location of the black king
     * 
     * @return always returns null
     */
    public Tile getBlackKing() {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (getTile(x, y).getPiece() instanceof King) {
                    if (!getTile(x, y).getPiece().getIsWhite()) {
                        return getTile(x, y);
                    }
                }
            }
        }
        return null;
    }

    public Board clone() {
        Board cloneBoard = new Board();

        // System.out.println(tempTile);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile currentTile = cloneBoard.getTile(i, j);
                if ((currentTile != null)) {
                    // System.out.println(tiles[i][j].getPiece());
                    // System.out.println(tiles[i][j]);
                    // System.out.println(currentTile);

                    if (tiles[i][j].getPiece() instanceof Pawn) {
                        // System.out.println("Instance of Pawn? " + (tiles[i][j].getPiece() instanceof
                        // Pawn));
                        if (tiles[i][j].getPiece().getIsWhite()) {
                            // System.out.println("Instance of White Pawn? ");
                            currentTile.setPiece(new Pawn(true, currentTile));
                            // System.out.println(currentTile.getPiece().getPieceID());
                        } else {
                            currentTile.setPiece(new Pawn(false, currentTile));
                        }
                    }

                    if (this.getTile(i, j).getPiece() instanceof Rook) {
                        if (this.getTile(i, j).getPiece().getIsWhite()) {
                            currentTile.setPiece(new Rook(true, currentTile));
                        } else {
                            currentTile.setPiece(new Rook(false, currentTile));
                        }
                    }

                    if (this.getTile(i, j).getPiece() instanceof Knight) {
                        if (this.getTile(i, j).getPiece().getIsWhite()) {
                            currentTile.setPiece(new Knight(true, currentTile));
                        } else {
                            currentTile.setPiece(new Knight(false, currentTile));
                        }
                    }

                    if (this.getTile(i, j).getPiece() instanceof Bishop) {
                        if (this.getTile(i, j).getPiece().getIsWhite()) {
                            currentTile.setPiece(new Bishop(true, currentTile));
                        } else {
                            currentTile.setPiece(new Bishop(false, currentTile));
                        }
                    }

                    if (this.getTile(i, j).getPiece() instanceof Queen) {
                        if (this.getTile(i, j).getPiece().getIsWhite()) {
                            currentTile.setPiece(new Queen(true, currentTile));
                        } else {
                            currentTile.setPiece(new Queen(false, currentTile));
                        }
                    }

                    if (this.getTile(i, j).getPiece() instanceof King) {
                        if (this.getTile(i, j).getPiece().getIsWhite()) {
                            currentTile.setPiece(new King(true, currentTile));
                        } else {
                            currentTile.setPiece(new King(false, currentTile));
                        }
                    }
                }
            }
        }
        // System.out.println(tiles);
        // System.out.println(tempTile);
        // cloneBoard.printBoard();
        return cloneBoard;
    }

    /**
     * This method is used to populate the board with pieces in their default
     * locations
     */
    public void setBoard() {
        /**
         * black pieces
         */
        tiles[0][7].setPiece(new Rook(false, tiles[0][7]));
        tiles[1][7].setPiece(new Knight(false, tiles[1][7]));
        tiles[2][7].setPiece(new Bishop(false, tiles[2][7]));
        tiles[3][7].setPiece(new Queen(false, tiles[3][7]));
        tiles[4][7].setPiece(new King(false, tiles[4][7]));
        tiles[5][7].setPiece(new Bishop(false, tiles[5][7]));
        tiles[6][7].setPiece(new Knight(false, tiles[6][7]));
        tiles[7][7].setPiece(new Rook(false, tiles[7][7]));
        tiles[0][6].setPiece(new Pawn(false, tiles[0][6]));
        tiles[1][6].setPiece(new Pawn(false, tiles[1][6]));
        tiles[2][6].setPiece(new Pawn(false, tiles[2][6]));
        tiles[3][6].setPiece(new Pawn(false, tiles[3][6]));
        tiles[4][6].setPiece(new Pawn(false, tiles[4][6]));
        tiles[5][6].setPiece(new Pawn(false, tiles[5][6]));
        tiles[6][6].setPiece(new Pawn(false, tiles[6][6]));
        tiles[7][6].setPiece(new Pawn(false, tiles[7][6]));
        /**
         * white pieces
         */
        tiles[0][0].setPiece(new Rook(true, tiles[0][0]));
        tiles[1][0].setPiece(new Knight(true, tiles[1][0]));
        tiles[2][0].setPiece(new Bishop(true, tiles[2][0]));
        tiles[3][0].setPiece(new Queen(true, tiles[3][0]));
        tiles[4][0].setPiece(new King(true, tiles[4][0]));
        tiles[5][0].setPiece(new Bishop(true, tiles[5][0]));
        tiles[6][0].setPiece(new Knight(true, tiles[6][0]));
        tiles[7][0].setPiece(new Rook(true, tiles[7][0]));
        tiles[0][1].setPiece(new Pawn(true, tiles[0][1]));
        tiles[1][1].setPiece(new Pawn(true, tiles[1][1]));
        tiles[2][1].setPiece(new Pawn(true, tiles[2][1]));
        tiles[3][1].setPiece(new Pawn(true, tiles[3][1]));
        tiles[4][1].setPiece(new Pawn(true, tiles[4][1]));
        tiles[5][1].setPiece(new Pawn(true, tiles[5][1]));
        tiles[6][1].setPiece(new Pawn(true, tiles[6][1]));
        tiles[7][1].setPiece(new Pawn(true, tiles[7][1]));

    }

    /**
     * This method prints out the 64 tiles and the information provided by each
     * tile. Checks if there is a piece on a tile, then prints out the pieceID of
     * specified piece if it exists, otherwise it will determine if the tile should
     * be a white space or black space and print either " " or "##" The function
     * will also append '1' to '8' on the right hand vertical side and 'a' to 'h' on
     * the horizontal base.
     */
    public void printBoard() {

        for (int row = 7; row >= 0; row--) {
            for (int col = 0; col < 8; col++) {
                if (tiles[col][row].getPiece() != null) {
                    System.out.print(tiles[col][row].getPiece().getPieceID());
                } else {
                    // if (tiles[col][row].getIsVulnerableTile()) {
                    // //System.out.print("pp");
                    if ((row + col) % 2 == 0) {
                        System.out.print("##");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.print(" ");
            }
            System.out.println(row + 1);
        }
        System.out.println(" a  b  c  d  e  f  g  h ");
    }
}
