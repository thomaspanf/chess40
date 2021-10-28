package src;

/**
 *
 * @author Thomas Pan
 * @author Alphan Yang
 * 
 */
public class Tile {

    private final int x;
    private final int y;
    private Piece piece;
    private final Board board;
    private boolean vulnerableTile;

    /**
     * This method is the contructor for a Tile object allows a Tile object to
     * reference piece, board, and the x y coordinates
     * 
     * @param x     represents x coordinate of tile
     * @param y     represents y coordinate of tile
     * @param piece represents piece object
     * @param board represents board object
     */
    public Tile(int x, int y, Piece piece, Board board) {
        this.x = x;
        this.y = y;
        this.piece = piece;
        this.board = board;
        vulnerableTile = false;
    }

    /**
     * getter for piece
     * 
     * @return returns the piece object currently on tile
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * setter for piece
     * 
     * @param piece represents piece object
     */
    public void setPiece(Piece piece) {

        this.piece = piece;
    }

    /**
     * getter for board
     * 
     * @return returns the board that current tile is part of
     */
    public Board getBoard() {

        return board;
    }

    /**
     * getter for x coordinate
     * 
     * @return returns x coordinate of specified tile
     */
    public int getX() {

        return x;
    }

    /**
     * getter for y coordinate
     * 
     * @return returns y coordinate of specified tile
     */
    public int getY() {

        return y;
    }

    /**
     * This function is used to get if a tile is vulnerable to en passant
     * 
     * @return returns boolean for if a tile is vulnerable
     */
    public boolean getIsVulnerableTile() {
        return vulnerableTile;
    }

    /**
     * This function is used to set a tile as a vulnerable tile
     */
    public void setVulnerableTile() {
        vulnerableTile = true;
    }

    /**
     * This function is used to set a tile as invulnerable to en passant
     */
    public void resetVulnerableTile() {
        vulnerableTile = false;
    }

}
