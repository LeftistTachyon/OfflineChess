package offlinechess;

import java.util.LinkedList;

/**
 * A class to represent any chess piece
 * @author Jed Wang
 */
public abstract class AbstractPiece {
    /**
     * Whether or not the piece is white
     */
    protected final boolean isWhite;
    
    /**
     * Creates a new AbstractPiece
     * @param isWhite whether or not the piece is white
     */
    public AbstractPiece(boolean isWhite) {
        this.isWhite = isWhite;
    }
    
    /**
     * Determines whether a move is legal
     * @param cb the current state of the chess game
     * @param fromWhere the current place of the piece
     * @param toWhere to where the piece would be moved
     * @return whether the move would be legal
     */
    public boolean isLegalMove(ChessBoard cb, String fromWhere, String toWhere) {
        return legalMoves(cb, fromWhere).contains(toWhere);
    }
    
    /**
     * Returns all of the legal moves this piece could make
     * @param cb the current state of the chess game
     * @param currentPosition the current place of the piece
     * @return all legal moves
     */
    public abstract LinkedList<String> allLegalMoves(ChessBoard cb, String currentPosition);
    
    public boolean isAllLegalMove(ChessBoard cb, String fromWhere, String toWhere) {
        return allLegalMoves(cb, fromWhere).contains(toWhere);
    }
    
    /**
     * Returns all of the legal moves this piece could make, taking into account check
     * @param cb the current state of the chess game
     * @param currentPosition the current place of the piece
     * @return the legal moves this piece can make
     */
    public LinkedList<String> legalMoves(ChessBoard cb, String currentPosition) {
        if(cb.inCheck(isWhite)) {
            LinkedList<String> allLegal = allLegalMoves(cb, currentPosition);
            LinkedList<String> output = new LinkedList<>();
            for(String s:allLegal) {
                ChessBoard copy = new ChessBoard(cb);
                copy.movePiece(currentPosition, s);
                if(!copy.inCheck(isWhite)) output.add(s);
            }
            return output;
        } else return allLegalMoves(cb, currentPosition);
    }
    
    /**
     * Returns all of the legal captures this piece could make
     * @param cb the current state of the chess game
     * @param currentPosition the current place of the piece
     * @return all legal captures
     */
    public abstract LinkedList<String> legalCaptures(ChessBoard cb, String currentPosition);
}