package offlinechess;

import java.util.ArrayList;

/**
 * A class that records the moves of a game
 * @author Jed Wang
 */
public class MoveRecorder {
    
    /**
     * Represents a pawn
     */
    public static final int PAWN = 0;
    
    /**
     * Represents a knight
     */
    public static final int KNIGHT = 1;
    
    /**
     * Represents a bishop
     */
    public static final int BISHOP = 2;
    
    /**
     * Represents a rook
     */
    public static final int ROOK = 3;
    
    /**
     * Represents a queen
     */
    public static final int QUEEN = 4;
    
    /**
     * Represents a king
     */
    public static final int KING = 5;
    /**
     * The collection of moves made in the game
     */
    private ArrayList<String> moves;
    
    /**
     * Default constructor
     */
    public MoveRecorder() {
        moves = new ArrayList<>();
    }
    
    /**
     * Creates a duplicate of the given MoveRecorder
     * @param mr the MoveRecorder to duplicate
     */
    public MoveRecorder(MoveRecorder mr) {
        moves = new ArrayList<>(mr.moves);
    }

    /**
     * Returns the moves made
     * @return the moves made
     */
    public ArrayList<String> getMoves() {
        return moves;
    }
    
    /**
     * Determines a String that denotes a move
     * @param fromWhere from where the piece is moved
     * @param toWhere to where the piece moved
     * @param whichPiece which piece moved
     * @param capture whether this piece is capturing something
     * @return the String that denotes the move
     */
    public String toMoveString(String fromWhere, String toWhere, int whichPiece, boolean capture) {
        String captureSymbol = (capture)?"x":"";
        switch(whichPiece) {
            case BISHOP:
                return "B" + captureSymbol + toWhere;
            case KING:
                return "K" + captureSymbol + toWhere;
            case KNIGHT:
                return "N" + captureSymbol + toWhere;
            case PAWN:
                return (capture)?fromWhere.substring(0, 1) + "x" + toWhere:toWhere;
            case QUEEN:
                return "Q" + captureSymbol + toWhere;
            case ROOK:
                return "R" + captureSymbol + toWhere;
            default:
                return "";
        }
    }
    
    /**
     * Determines a String that denotes a move <br/>
     * Used when a move is ambiguous and the file is needed for clarification
     * @param toWhere to where the piece moved
     * @param column which file this piece is moved from
     * @param whichPiece which piece moved
     * @param capture whether this piece is capturing something
     * @return the String that denotes the move
     */
    public String toMoveString(String toWhere, int column, int whichPiece, boolean capture) {
        String captureSymbol = (capture)?"x":"";
        String file = "" + (char)(column + 'a');
        switch(whichPiece) {
            case BISHOP:
                return "B" + file + captureSymbol + toWhere;
            case KING:
                return "K" + file + captureSymbol + toWhere;
            case KNIGHT:
                return "N" + file + captureSymbol + toWhere;
            case PAWN:
                return (capture)?file + "x" + toWhere:toWhere;
            case QUEEN:
                return "Q" + file + captureSymbol + toWhere;
            case ROOK:
                return "R" + file + captureSymbol + toWhere;
            default:
                return "";
        }
    }
}